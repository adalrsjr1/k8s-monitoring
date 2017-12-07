#!/bin/bash

# PID_1 is the JAVA PID
PID_1=$1
# PROC is the Z3 name
PROC=$2
JAVA=$(ps -p $PID_1 -o %cpu,%mem | tail -n 1)

pat="\D+"

while [[ $JAVA != *"CPU"* ]]; do
  PID_2=$(ps aux | grep $PROC | grep -v grep | awk '{print $2}' | head -n 1)
  JAVA=$(ps -p $PID_1 -o %cpu,%mem | tail -n 1)
  Z3=$(ps -p $PID_2 -o %cpu,%mem | tail -n 1)

  echo "$JAVA    $Z3"
  sleep 1
done
