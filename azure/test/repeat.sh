#!/bin/bash
N=$1
for i in $(seq 1 $N); do
  { ./run.sh -d 1 -h k8s.brazilsouth.cloudapp.azure.com:30001 -c 100 -r 10000 ; } &>> result.txt
done
