#!/bin/bash

if [ $# -ne 2 ]; then
   echo "usage: ./create-vm <name> <# replicas>"
   exit 1
fi

NAME=$1
N=$2

for i in $(seq 1 $N); do
  echo $NAME$i
  az vm delete -g adalberto -n $NAME$i --yes --no-wait
done
