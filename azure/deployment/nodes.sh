#!/bin/bash

if [ $# -ne 2 ]; then
  echo "usage ./nodes.sh <join|reset> <# nodes>"
  exit 1
fi

N=$1

if [ $1 == 'join' ]; then
  ssh -t micro@master.brazilsouth.cloudapp.azure.com 'echo "" > /home/micro/.ssh/known_hosts'
  ssh -t micro@master.brazilsouth.cloudapp.azure.com './join_nodes.sh $N'
fi

if [ $1 == 'reset' ]; then
  ssh -t micro@master.brazilsouth.cloudapp.azure.com './reset_nodes.sh $N'
fi
