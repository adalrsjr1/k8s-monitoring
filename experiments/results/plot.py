#!/usr/bin/python3

from pprint import pprint
import numpy as np
from matplotlib import pyplot as plt
import math
import re
import json
import sys

def main():
  table = parse()
  pprint(table)
  plot(table)

def parse():
  dtype = [('name', 'S25'), ('mean', float), ('deviation', float)]
  value = []
  
  with open('results.out','r') as f:
    line = f.readline()
    line = f.readline()
    while line:
      l = line.strip()
      aux = l.split('{')
      l = '{' + aux[1]
      
      name = parseName(aux[0])
      data = json.loads(l)
      
      value.append((name, data['mean'], data['std']))

      line = f.readline()

  table = np.array(value, dtype)
  table = np.sort(table, order='mean')
  return table

def parseName(trash):
  name = trash.split(' ')[0]
  name = name.split('.')[0]
  return name

def plot(table):
  mean = table['mean']
  dev = table['deviation']
  names = table['name']

  nnames = []
  for n in names:
    nnames.append(n.decode('UTF-8'))

  names = np.array(nnames)

  ind = np.arange(len(mean))

  width = 0.8

  bar = plt.bar(ind, mean, width, yerr=dev)

  x = np.arange(len(names))

  cells(names)

  plt.subplots_adjust(left=0.2, bottom=0.8)

  #plt.xticks(x, names, rotation='vertical')
  plt.xticks([])
  plt.title('Sock Shop improvement')
  plt.ylabel('Round trip requests (ms)')
 # plt.xlabel('Configurations')
  plt.tight_layout()
  #plt.show()
  plt.savefig('sock-shop.pdf', bbox_inches='tight', orientation='landscape',\
      papertype='b0', dpi=300)

def cells(columns):
  rows = ['being used', 'saved']
  l = [15, 12, 12, 13, 12, 15, 12]
  saved = [0, 3, 3, 2, 3, 0, 3]

  the_table = plt.table(cellText=[l, saved], \
                        rowLabels=rows,
                        colLabels=columns,
                        loc='bottom')




if __name__ == '__main__':
   main()
