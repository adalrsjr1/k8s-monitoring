#!/bin/bash

if [ $# -ne 1 ]; then
  echo "usage ./master <start|stop>"
  exit 1
fi

if [ $1 == start ]; then
  echo starting master
  az vm start -g adalberto -n master
elif [ $1 == stop ]; then
  echo stopping master
  az vm stop -g adalberto -n master
fi
