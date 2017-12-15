#!/usr/bin/python3

from pprint import pprint
import numpy as np
from matplotlib import pyplot as plt
import math
import re
import json
import sys

def mainFullPlot(plotHeader, save):
  filesNames = getFilesNames()
  data = fullData(filesNames)
  plot(plotHeader, data, save)

def getFilesNames():
  return ['moves-benchmark.jsonp']

def fullData(filesNames):
  movesJson= loadJson(filesNames[0])

  movesData = getValues(movesJson)
  data = [movesData]
  data = fillData(data)

  return data

def fillData(data):
  moves = data[0]

  values = createAxisxMove()
  for value in values:
    if not value in moves['name']:
      nMoves = np.append(moves, np.array([(value, -1.0, -1.0)], dtype=moves.dtype))
  moves = np.sort(moves, order='name')
    
  return [moves]

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

  names = createAxisxMove()
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
  moves = explodedMethodName[0].strip('test')
  return moves

def plot(header, data, save):
  movesData= data[0]
  meanMoves = movesData['mean'] 
  devMoves = movesData['deviation']

  x = movesData['name']
  
  plt.plot(x, meanMoves)
  plt.errorbar(x, meanMoves, yerr=devMoves, markeredgewidth=2, capsize=2,\
      ecolor='k', capthick=1, lw=1)
  plt.title(header)
  plt.ylabel('Average Time (s)')
  plt.xlabel('Number of Movements')

  if not save:
    plt.show()
  else:
    plt.savefig(header+'.pdf', bbox_inches='tight', orientation='landscapes', \
        papertype='b0', dpi=300)

def stackPlot(header, data, save):
  movesData= data[0]
  meanMoves = movesData['mean'] 
  devMoves = movesData['deviation']
  
  ind = np.arange(len(meanMoves))

  width = 0.8 # the width of bars: can also be len(x) sequence

  bar = plt.bar(ind, meanMoves, width, yerr=devMoves, color='#d62728')

  x = np.arange(len(movesData['name']))

  axisX = createAxisxMove()
  plt.xticks(x, axisX, rotation='vertical')
  plt.title(header)
  plt.ylabel('Average Time (s)')
  plt.xlabel('Number of Movements')
  if not save:
    plt.show()
  else:
    plt.savefig(header+'.pdf', bbox_inches='tight', orientation='landscape', \
        papertype='b0', dpi=300)

def createAxisxMove():
  to10 = np.array([i for i in range(1,11)])
  to100 = to10 * 10
  to1000 = to10 * 100

  axisX = np.concatenate((to10, to100))
  axisX = np.concatenate((axisX, to1000))

  axisX = np.unique(axisX)

  return axisX

if __name__ == '__main__':
  save = sys.argv[1]

  if save == 'false' or save == '0':
      save = False
  else:
    save = True
  
  plotHeader = 'Number of Movements'
  mainFullPlot(plotHeader, save)
