#!/usr/bin/python

import numpy as np
import re
import json
import sys

def isSeparator(line):
  return re.match('[-]+', line)

def isResult(line):
  return line.split()[-1] == 'req/s'

def isPerc(line):
  return line.split()[-1] == '100%'

def isError(line):
  return line.split()[-1] == 'Error'

def getResults(it, results, line, lastLine):
  lastLine = line
  i, line = next(it)

  while not isSeparator(line):
    results.append(line)
    lastLine = line
    i, line = next(it)
  return results

def parse(filename):
  with open(filename, 'r') as f:
    fileIt = enumerate(f)
    i, lastLine = next(fileIt)

    allResults = []
    while True:
      try:
        i, line = next(fileIt)

        results = []
        percs = []
        errors = []
  
        while not isSeparator(line):
          lastLine = line
          i, line = next(fileIt)
  
        if isResult(lastLine):
          getResults(fileIt, results, line, lastLine)
          allResults.append({'results':results})
        elif isPerc(lastLine):
          getResults(fileIt, percs, line, lastLine)
          allResults.append({'percs':percs})
        elif isError(lastLine):
          getResults(fileIt, errors, line, lastLine)
          allResults.append({'errors':errors})
  
      except StopIteration:
        break
  return allResults  

class Result:
  def __init__(self, arr):
    self.method = arr[0]
    self.name = arr[1]
    self.reqs = arr[2]
    s = arr[3]
    self.fails = s[arr[3].find("(")+1:arr[3].find(")")][:-1]
    self.avg = arr[4]
    self.min = arr[5]
    self.max = arr[6]
    self.median = arr[8]
    self.req = arr[9]

class Perc:
  def __init__(self, arr):
    self.method = arr[0]
    self.name = arr[1]
    self.reqs = arr[2]
    self._50 = arr[3]
    self._66 = arr[4]
    self._75 = arr[5]
    self._80 = arr[6]
    self._90 = arr[7]
    self._95 = arr[8]
    self._98 = arr[9]
    self._99 = arr[10]
    self._100 = arr[11]

class Error:
  def __init__(self, arr):
    self.occurence = arr[0]
    self.method = arr[1]
    self.name = arr[2]
    self.message = arr[3]

def reject_outliers(data, m=2):
    return data[abs(data - np.mean(data)) < m * np.std(data)]

def avg(filename, endpoint, attrName):
  allResults = parse(filename)

  results = []
  percs = []
  errors = []
  
  for entry in allResults:
    for k,v in entry.items():
      if k == 'results':
        for elem in v:
          results.append(Result(elem.split()))

  summation = 0
  values = []
  for elem in results:
    if elem.name == endpoint:
      try:
        v = int(getattr(elem, attrName))
      except ValueError:
        v = float(getattr(elem, attrName))
      values.append(v)
  
  arr_without_outliers = reject_outliers(np.array(values))

  return {"mean":np.mean(arr_without_outliers), \
         "std":np.std(arr_without_outliers)}

def usage():
  print 'parse.py <filename> /<endpoint> <reqs|fails|avg|min|max|median|reqs/s>'

def main(argv):
  if len(argv) != 4:
    return usage()
  filename = argv[1]
  endpoint = argv[2]
  attribute = argv[3]
  print filename, endpoint, attribute,\
        json.dumps(avg(filename, endpoint, attribute))

if __name__ == "__main__":
  main(sys.argv)
