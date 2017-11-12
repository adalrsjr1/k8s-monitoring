#!/bin/bash

if [ $# -ne 3 ]; then
   echo "usage: ./create-vm <name> <# replicas> <ssh_key>"
   exit 1
fi

NAME=$1
N=$2
SSH_KEY=$3
SIZE="Basic_A2"

for i in $(seq 1 $N); do
  echo $NAME$i
  az vm create --resource-group adalberto --location brazilsouth --name $NAME$i \
--image k8s-base --admin-username micro --ssh-key-value $SSH_KEY --size $SIZE \
--no-wait
done
