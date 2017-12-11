#!/usr/bin/python3

from pprint import pprint
import numpy as np
from matplotlib import pyplot as plt
import math
import re
import json
import sys

def mainFullPlot(numberMessages, typePlanning, plotHeader, save):
  filesNames = getFilesNames(numberMessages, typePlanning)
  data = fullData(filesNames)
  stackPlot(plotHeader, data, save)



def getFilesNames(numberMessages, typePlanning):
  prefix = 'model-'

  creation = prefix + 'creation-' + numberMessages + '_messages.jsonp'
  analysis = prefix + 'analysis-' + numberMessages + '_messages.jsonp'
  planning = prefix + 'planning-' + typePlanning + '-' + numberMessages + '_messages.jsonp'

  return [creation, analysis, planning]

def fullData(filesNames):
  creationJson = loadJson(filesNames[0])
  analysisJson = loadJson(filesNames[1])
  planningJson = loadJson(filesNames[2])

  creationData = getValues(creationJson)
  analysisData = getValues(analysisJson)
  planningData = getValues(planningJson)

  data = [creationData, analysisData, planningData]
  data = fillData(data)

  data[0] = np.sort(data[0], order='mean')
  data[1] = np.sort(data[1], order='mean')
  data[2] = np.sort(data[2], order='mean')

  return data

def fillData(data):
  creation = data[0]
  analysis = data[1]
  planning = data[2]

  values = createAxisX()
  for value in values:
    if not value in creation['name']:
      nCreation = np.append(creation, np.array([(value, -1.0, -1.0)], dtype=creation.dtype))
    if not value in analysis['name']:
      analysis = np.append(analysis, np.array([(value, -1.0, -1.0)], dtype=creation.dtype))
    if not value in planning['name']:
      planning = np.append(planning, np.array([(value, -1.0, -1.0)], dtype=creation.dtype))

    

  creation = np.sort(creation, order='name')
  analysis = np.sort(analysis, order='name')
  planning = np.sort(planning, order='name')
    
  for i in range(values.size):
    updateValue(creation, i)
    updateValue(analysis, i)
    updateValue(planning, i)

    print(creation[i], analysis[i], planning[i])

  return [creation, analysis, planning]

def updateValue(data, i):
  toChange = checkCurrentValue(data, 'mean', i)
  if toChange:
    newMean = calculateValueToFill(data, 'mean', i)
    newDeviation = calculateValueToFill(data, 'deviation', i)
    setValue(data, 'mean', i, newMean)
    setValue(data, 'deviation', i, newDeviation)
  

def checkCurrentValue(data, header, pos):
  if data[pos][header] < 0.0:
    return True
  return False

def calculateValueToFill(data, header, pos):
  size = data.size

  value = 0.0
  if pos >= size:
    value = data[pos-1][header] * 2
  elif pos == 0:
    value = data[pos+1][header] / 2
  else:
    i = pos
    value = (data[i+1][header] + data[i-1][header]) / 2

  return value

def setValue(data, header, pos, value):
  data[pos][header] = value

def loadJson(filename):

  with open(filename, 'r') as jsonp:
    data = jsonp.read().replace('\n','')
    parsed_jsonp = data.split('(')[1].strip(');')
    parsed_json = json.loads(parsed_jsonp)

  return parsed_json

def getValues(experimentValues):
  
  dtype = [('name', int), ('mean', float), ('deviation',float)]
  values = []

  names = createAxisX()
  for rows in experimentValues['rows']:
    methodName = parseMethodName(rows['c'][0]['v'])
    avgTimeRow = rows['c'][1]['v']
    stdDevRow = rows['c'][2]['v']
    
    if int(methodName) in names:
      values.append((methodName, avgTimeRow, stdDevRow))

  nvalues = np.array(values, dtype)
  return nvalues

def parseMethodName(originalMethodName):
  explodedMethodName = originalMethodName.split('_')
  messages = explodedMethodName[1]
  services = explodedMethodName[3]

  return services

def stackPlot(header, data, save):
  creationData, analysisData, planningData = data[0], data[1], data[2]

  meanCreation = creationData['mean'] 
  meanAnalysis = np.subtract(analysisData['mean'], meanCreation)
  meanPlanning = np.subtract(planningData['mean'], analysisData['mean'])
  
#  devCreation = creationData['deviation']
#  devAnalysis = analysisData['deviation']
  devPlanning = planningData['deviation'] * 0.33334
  
  ind = np.arange(len(meanCreation))

  width = 0.8 # the width of bars: can also be len(x) sequence

  creationBar = plt.bar(ind, meanCreation, width, color='#d62728')

  analysisBar = plt.bar(ind, meanAnalysis, width, color='#28d627', \
      bottom=meanCreation)

  planningBar = plt.bar(ind, meanPlanning, width, color='#2728d6', \
      bottom=meanCreation, yerr=devPlanning)

  x = np.arange(len(creationData['name']))

  axisX = createAxisX()
  plt.xticks(x, axisX, rotation='vertical')
#  plt.yticks(np.arange(0,10,1))
  plt.title(header)
  plt.ylabel('Average Time (s)')
  plt.xlabel('Number of Services and Hosts')
  if not save:
    plt.show()
  else:
    plt.savefig(header+'.pdf', bbox_inches='tight', orientation='landscape', \
        papertype='b0', dpi=300)

def plot(header, data):
  x = np.arange(len(data['name']))
  y = data['mean']
  error = data['deviation']

  fig, ax = plt.subplots()

  ax.errorbar(x, y, yerr=error, fmt='-o')

  axisX = createAxisX()
  plt.xticks(x, axisX, rotation='vertical')
  plt.title(header)
  plt.ylabel('Average Time (s)')
  plt.xlabel('Number of Services and Hosts')
  plt.show()

_typePlanning = None
def createAxisX():
  if _typePlanning == 'Heuristic':
    value = createAxisXHeuristic()
    return value
  if _typePlanning == 'SMT Solver' or _typePlanning == 'SMT Optimizer':
    value = createAxisXSMT()
    return value
  return None

def createAxisXHeuristic():
  to10 = np.array([i for i in range(1,11)])
  to20 = to10 + 10
  to100 = to10 * 10
  to1000 = to10 * 100
  
  axisX = np.concatenate((to20, to100))
  axisX = np.concatenate((axisX, to1000))

  axisX = np.unique(axisX)

  return axisX

def createAxisXSMT():
  return np.array([11,12,13,14,15,20,30])

if __name__ == '__main__':
  typePlanning = sys.argv[1]
  save = sys.argv[2]

  if typePlanning == 'smt_solver':
    _typePlanning = 'SMT Solver'
  elif typePlanning == 'smt':
    _typePlanning = 'SMT Optimizer'
  elif typePlanning == 'heuristic':
    _typePlanning = 'Heuristic'

  if save == 'false' or save == '0':
      save = False
  else:
    save = True
  
  for n in range(1, 6):
    numberMessages = str(10**n)
    plotHeader = 'Performance Chart: Planning by ' + _typePlanning + ' with ' + \
        numberMessages + ' Messages'
  
    mainFullPlot(numberMessages, typePlanning, plotHeader, save)

#experimentValues = loadJson(sys.argv[1])
#values = getValues(experimentValues)
#pprint(values)
#plot('Model Creation', values)
