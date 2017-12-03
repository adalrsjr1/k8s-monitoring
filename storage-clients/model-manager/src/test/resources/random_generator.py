#!/usr/bin/env python

import sys
import random
import time
import json
from pprint import pprint
import networkx as nx
import matplotlib.pyplot as plt

random.seed(31)

def randomHosts(prefix, n):
  hosts = [None] * n

  randN = random.randint(0,n-1)
  hostName = prefix + str( randN )
  nones = n
  while nones != 0:
    if not hosts[randN]:
      hosts[randN] = {\
        'name': hostName,\
        'limits': {'cpu':4000, 'memory': 1000000 }, \
        'hostAddress': [hostName], \
      }
      nones = nones - 1
    randN = random.randint(0,n-1)
    hostName = prefix + str( randN )

  return hosts

def randomServices(hostPrefix, nHost, svcPrefix, nSvc):
  services = []

  uid = 0
  while len(services) < nHost:
    name = svcPrefix + str(uid)
    hostName = hostPrefix + str(uid)
    services.append({
        'uid': name,
        'name': name,
        'application': 'APP',
        'hostAddress': hostName
      })
    uid = uid + 1

  return services

def randomMetrics(hostPrefix, nHost, svcPrefix, nSvc):
  metrics = {
    'hosts' : {
        'cpu': {
            'node_capacity': {},
            'node_utilization': {},
          },
        'memory': {
            'node_capacity': {},
            'node_utilization': {}
          }
      },
    'services' : {
        'cpu':{
            'usage': {},
            'limit': {}
          },
        'memory':{
            'usage': {},
            'limit': {}
          }
      },
  }

  for i in range(0, nHost):
    host = hostPrefix + str(i)
    metrics['hosts']['cpu']['node_capacity'][host] = 4000
    metrics['hosts']['cpu']['node_utilization'][host] = 0
    metrics['hosts']['memory']['node_capacity'][host] = 4.2597956608E10
    metrics['hosts']['memory']['node_utilization'][host] = 0

  for i in range(0, nSvc):
    svc = svcPrefix + str(i)
    metrics['services']['cpu']['usage'][svc] = random.randint(1,500)
    metrics['services']['cpu']['limit'][svc] = 0
    metrics['services']['memory']['usage'][svc] = random.randint(1,500)
    metrics['services']['memory']['limit'][svc] = 0

  return metrics
 

def randomMessage(svcPrefix, nSvc, cache):
  t = int(round(time.time() * 1000000000))

  size = nSvc
  source = ''
  target = ''

  while source == target:
    source = svcPrefix + str(random.randint(0,nSvc-1))

    if source not in cache:
      target = svcPrefix + str(random.randint(0,nSvc-1))
    else:
      target = cache[source]
    cache[source] = target

  return {"correlationId": t, "timestamp": t, "totalSize": random.randint(1,10),\
  "totalTime" : random.randint(1,100), "targetIp": target, "sourceIp": source,\
  "sourceName": source, "targetName": target}

cache = {}
def randomMessages(svcPrefix, nSvc, nMessages):
  messages = []
  for i in range(0,nMessages):
    messages.append(randomMessage(svcPrefix, nSvc, cache))

  return messages

def randomSparseGraph(v, p):  
  isConnected = False
  increment = p 
  g = None
  while not isConnected:
    g = nx.fast_gnp_random_graph(v,p)
    p = p + increment
    isConnected = nx.is_connected(g)
  return g
  #nx.write_adjlist(g, sys.stdout)
  #print g.edges()
  print(g.number_of_edges())
  print(p)
  #nx.draw(g)
  #plt.show()

def randomMessagesGraph(prefix, nSvc, nMessages):
  g = randomSparseGraph(nSvc, 0.00001)

  messages = []
  edges = g.edges()
  edges_size = len(edges)

  for e in edges:
    messages.append( messageInstance( prefix+str(e[0]), prefix+str(e[1]) ) )

  for i in range(nMessages):
    index = random.randint(0, edges_size-1)
    messages.append( messages[index] )

  return messages
 
def messageInstance(source, target):
  t = int(round(time.time() * 1000000000))

  return {"correlationId": t, "timestamp": t, "totalSize":int(random.gauss(100,10)),\
  "totalTime" : int(random.gauss(100,10)), "targetIp": target, "sourceIp": source,\
  "sourceName": source, "targetName": target}

def generateFiles(nHosts, nSvcs, nMsgs):
  path = '/home/adalrsjr1/Code/ibm-stack/benchmarking-input/'

  with open(path+'hosts-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as hostsFile:
    hostsFile.write(json.dumps(randomHosts('micro_', nHosts)))

  with open(path+'svcs-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as svcsFile:
    svcsFile.write(json.dumps(randomServices('micro_', nHosts, 'svc_', nSvcs)))

  with open(path+'msgs-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as msgsFile:
    msgsFile.write(json.dumps(randomMessagesGraph('svc_', nSvcs, nMsgs)))

  with open(path+'metrics-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as metricsFile:
    metricsFile.write(json.dumps(randomMetrics('micro_', nHosts, 'svc_', nSvcs)))
 
#nSvcs = int(sys.argv[1])
#nHosts = int(sys.argv[2])
#nMsgs = int(sys.argv[3])


nSvcs = 10
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 11
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 12
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 13
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 14
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 15
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 20
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 50
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 100
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)
nSvcs = 1000
nHosts = nSvcs
generateFiles(nSvcs,nHosts,10)
generateFiles(nSvcs,nHosts,100)
generateFiles(nSvcs,nHosts,1000)
generateFiles(nSvcs,nHosts,10000)
generateFiles(nSvcs,nHosts,100000)
generateFiles(nSvcs,nHosts,1000000)

