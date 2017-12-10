#!/usr/bin/python3

from pprint import pprint
import numpy as np
from scipy.interpolate import griddata
from matplotlib.colors import LogNorm
import matplotlib as mpl
from matplotlib import pyplot as plt
import math
import re

def parse(file):
  savings_dict = {}
  with open(file,'r') as savings_file:
    for line in savings_file:
      clean_line = line.strip('\n')
      if clean_line:
        key, value = clean_line.split('=')
        if not key in savings_dict:
          savings_dict[key] = []
        savings_dict[key].append(int(value))

  for item in savings_dict.items():
    value = item[1]
    s = 0
    for i in value:
      s += i
    savings_dict[item[0]] = math.ceil(float(s) / len(value))

  return savings_dict   

def splitData(data):
  charts_data = {}
  for item in data.items():
    header, label = splitLabel(item[0])
    value = item[1]
    
    if not header in charts_data:
      charts_data[header] = {}
    charts_data[header][label] = value

  return charts_data

def splitLabel(label):
  clean_label = label.split('BenchmarkingPlanning')
  clean_label = re.split('(_m_)10+',clean_label[1])
  null, svc, host, msg = clean_label[2].split('_')
  #formatted = '{:04d}_{:06d}'.format(int(svc), int(msg))
  formatted = '{:06d}_{:04d}'.format(int(msg), int(svc))
  return (clean_label[0], formatted)

def plot(header, data):
  labels = data.keys()
  values = data.values()
  # sorting both together
  # labels, values = zip( *sorted( zip( list( data.keys() ), list( data.values() ) ) ) )

  plt_x, plt_y, plt_z = np.array([]), np.array([]), np.array([])
  for l, v in data.items():
    a, b = l.split('_')
    plt_x = np.append(plt_x, int(a))
    plt_y = np.append(plt_y, int(b))
    plt_z = np.append(plt_z, v)

  xi = np.linspace(plt_x.min(), plt_x.max(), 1000)
  yi = np.linspace(plt_y.min(), plt_y.max(), 1000)
  zi = griddata((plt_x, plt_y), plt_z, (xi[None,:], yi[:,None]), method='cubic')


  cs = plt.contourf(xi, yi, zi, cmap=plt.cm.rainbow, vmax=plt_z.max(),\
  vmin=plt_z.min())
  plt.title(header)
  plt.colorbar()
  plt.show()

def plot2(header, data):
  x = np.array([])
  y = np.array([])
  v = np.array([])
  map_x = {}
  map_y = {}

  map_v = {}

  count_x = 0
  count_y = 0

  for l, value in data.items():
    a, b = l.split('_')
    a, b = int(a), int(b)
    
    x = np.append(x, a)
    y = np.append(y, b)

    map_v[(b,a)] = value

    if not a in map_x:
      map_x[a] = count_x
      count_x += 1

    if not b in map_y:
      map_y[b] = count_y
      count_y += 1

  A = np.zeros((len(map_y), len(map_x)))

  for l, value in data.items():
    a, b = l.split('_')
    a, b = int(a), int(b)
    print(a, b, map_v[(b,a)])
    A[ map_y[b] ][ map_x[a] ] = map_v[(b,a)]

  plt.title(header)
  plt.figure(1)
  plt.axis(list(map_y.keys()))
  plt.imshow(A, interpolation='nearest')
  plt.grid(True)
  plt.colorbar()
  
  plt.show()

def table(header, data):
  
  table = {}
  table2 = np.array([])
  for l, value in data.items():
    msg, svc = l.split('_')
    msg, svc = int(msg), int(svc)

    table2 = np.append(table2, [svc, msg, value])

    if not svc in table:
      table[svc] = {}
    table[svc][msg] = value

  return header, np.reshape(table2, (-1,3))

def plotTable(header, table): 
  # 5 bars 10 100 1000 10000 100000
  fig = plt.figure()
  ax = fig.add_subplot(111)

  space = 0.3

  conditions = np.unique(table[:,0])
  categories = np.unique(table[:,1])
  
  n = len(conditions)

  width = (1-space) / (len(conditions))

  bars = []
  for i, cond in enumerate(conditions):
    indeces = range(1, len(categories)+1)
    vals = table[table[:,0] == cond][:,2].astype(np.int32)
    pos = [j - (1-space) / 2. + i * width for j in range (1, len(categories)+1)]
    bars.append(ax.bar(pos, vals, width=width))

  ax.set_xticks(indeces)
  ax.set_xticklabels(categories)
  plt.setp(plt.xticks()[1], rotation=90)
  ax.set_ylabel("Savings")
  ax.set_xlabel("Services and Hosts")

#https://stackoverflow.com/questions/4700614/how-to-put-the-legend-out-of-the-plot/43439132#43439132
  plt.legend(bars, conditions.astype(np.int32), \
             bbox_to_anchor=(0,1.02,1,0.2), loc="lower left",\
                mode="expand", borderaxespad=0, ncol=10) 
  plt.title(header)
  plt.savefig(header+'.pdf', bbox_inches='tight', orientation='landscape',\
    papertype='b0', dpi=1000)
  #plt.show()

data = parse('savings.txt')
dataSplit = splitData(data)

for item in dataSplit.items():
  #plot2(item[0],item[1])
  header, t = table(item[0], item[1])
  plotTable(header, t)
