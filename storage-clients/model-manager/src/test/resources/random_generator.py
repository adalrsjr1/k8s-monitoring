#!/usr/bin/env python

import sys
import random
import time
import json

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
    hostName = hostPrefix + str(random.randint(0, nHost-1))
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
 

cache = dict()
def randomMessage(svcPrefix, nSvc):
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

def randomMessages(svcPrefix, nSvc, nMessages):
  messages = []
  for i in range(0,nMessages):
    messages.append(randomMessage(svcPrefix, nSvc))

  return messages

#if len(sys.argv) == 2:
#  sys.stdout = open("messages.json", "w")
#  for i in range(0, int(sys.argv[1])):
#    print json.dumps(randomMessage(services))
#else:
#  exit()

nSvcs = int(sys.argv[1])
nHosts = int(sys.argv[2])
nMsgs = int(sys.argv[3])

path = '/home/adalrsjr1/Code/ibm-stack/benchmarking-input/'

random.seed(31)

with open(path+'hosts-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as hostsFile:
  hostsFile.write(json.dumps(randomHosts('micro_', nHosts)))

with open(path+'svcs-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as svcsFile:
  svcsFile.write(json.dumps(randomServices('micro_', nHosts, 'svc_', nSvcs)))

with open(path+'msgs-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as msgsFile:
  msgsFile.write(json.dumps(randomMessages('svc_', nSvcs, nMsgs)))

with open(path+'metrics-random-'+str(nHosts)+'-'+str(nSvcs)+'-'+str(nMsgs)+'.json', 'w') as metricsFile:
  metricsFile.write(json.dumps(randomMetrics('micro_', nHosts, 'svc_', nSvcs)))
