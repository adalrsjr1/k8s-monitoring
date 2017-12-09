#!/bin/bash

CONFIG=$1

kubectl --kubeconfig $CONFIG apply -f influxdb.yaml
kubectl --kubeconfig $CONFIG apply -f heapster.yaml
kubectl --kubeconfig $CONFIG apply -f grafana.yaml
kubectl --kubeconfig $CONFIG apply -f heapster-rbac.yaml


