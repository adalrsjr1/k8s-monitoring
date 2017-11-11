#!/usr/bin/python

import sys
import re
import json
import yaml

def get_custom_config(path, key):
  with open(path, 'r') as data_file:
    data = json.load(data_file)
    
    for elem in data:
      if elem['name'] == key:
        return elem['deployment']

def split_k8s_file(path):
  with open(path, 'r') as data_file:
    s = ''
    d = {}
    for i, line in enumerate(data_file):
      if re.match(r'---',line):
        k8s_deploy = yaml.load(s)
        name_deploy = get_k8s_name(k8s_deploy)
        d[name_deploy] = k8s_deploy
        s = ''
      else:
        s += line
    return d

def get_k8s_name(k8s):
  if isinstance(k8s, dict) and k8s['kind'] == 'Deployment':
    return k8s['metadata']['name']
  else:
    return ''

def get_k8s_node(k8s):
  if isinstance(k8s, dict) and k8s['kind'] == 'Deployment':
    return \
    k8s['spec']['template']['spec']['nodeSelector']['kubernetes.io/hostname']
  else:
    return ''

def set_k8s_node(k8s, value):
  if isinstance(k8s, dict) and k8s['kind'] == 'Deployment':
    k8s['spec']['template']['spec']['nodeSelector']['kubernetes.io/hostname'] =\
    value

def create_file(name):
  with open(name, 'w') as file:
    file.write('---\n')

def append_file(name, text):
  with open(name, 'a') as file:
    file.write(text)

def main(name):
  k8ss = split_k8s_file('complete-demo-azure.yaml')
  config = get_custom_config('map.json', name)

  for key, value in config.items():
    set_k8s_node(k8ss[key], value)

  create_file(name+'.yaml')
  for key, value in k8ss.items():
    text = yaml.safe_dump(value)
    append_file(name+'.yaml', text)
    append_file(name+'.yaml', '---\n')

if len(sys.argv) == 2:
  name = sys.argv[1]
  main(name)
