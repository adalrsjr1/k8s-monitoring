#!/usr/bin/env python

import sys
import random
import time
import json

def randomMessage(services):
  t = int(round(time.time() * 1000))

  size = len(services)-1

  source = ''
  target = ''

  while source == target:
    source = services[random.randint(0,size)]
    target = services[random.randint(0,size)]

  return {"correlationId": t, "timestamp": t, "totalSize": random.randint(1,10),\
  "totalTime" : random.randint(1,100), "targetIp": "0.0.0.0", "sourceIp": "0.0.0.0",\
  "sourceName": source, "targetName": target}


services = ["carts-75958c9dc6-b6478", "carts-db-787f4b7896-9xtwl", "catalogue-6c69b85d67-rslg6", "catalogue-db-64989577db-7mvgp", "front-end-6ffc4ccbb9-tfxhb", "orders-9c66f8db6-6kscv", "orders-db-66f56c7d6d-hqnzr", "payment-748bb4dbdb-r6tz8", "queue-master-599cfcc7-mqgf8", "shipping-76cfc6d787-6s9bd", "user-767d6bb97f-bptj2", "user-db-65585649f9-nfwv9"]


if len(sys.argv) == 2:
  sys.stdout = open("messages.json", "w")
  for i in range(0, int(sys.argv[1])):
    print json.dumps(randomMessage(services))
else:
  exit()
