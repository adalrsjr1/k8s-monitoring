#!/bin/bash

CONFIG=../admin.conf

kubectl --kubeconfig $CONFIG create -f influxdb.yaml
kubectl --kubeconfig $CONFIG create -f heapster.yaml
kubectl --kubeconfig $CONFIG create -f grafana.yaml
kubectl --kubeconfig $CONFIG create -f heapster-rbac.yaml


