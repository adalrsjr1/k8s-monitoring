#!/bin/bash

if [ $# -eq 4 ]; then
  echo "usage ./manage-vm <start|stop> <name> <# vms>"
  exit 1
fi

NAME=$2
N=$3

if [ $1 == "start" ]; then
  for i in $(seq 1 $N); do
    echo starting nodes $NAME$i
    az vm start -g kubernetes -n $NAME$i --no-wait
  done
fi

if [ $1 == "stop" ]; then
  for i in $(seq 1 $N); do
    echo stopping nodes $NAME$i
    az vm deallocate -g kubernetes -n $NAME$i --no-wait
  done
fi

