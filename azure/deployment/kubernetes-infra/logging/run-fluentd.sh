#!/bin/bash
# do not use this script -- the logging must be provided by the apps
echo "ensure that sudo sysctl -w vm.max_map_count=262144 was applied in all k8s nodes"

CONFIG=$1

kubectl --kubeconfig $CONFIG create -f es-service.yaml
kubectl --kubeconfig $CONFIG create -f es-statefulset.yaml
kubectl --kubeconfig $CONFIG create -f fluentd-es-configmap.yaml
kubectl --kubeconfig $CONFIG create -f fluentd-es-ds.yaml
kubectl --kubeconfig $CONFIG create -f kibana-deployment.yaml
kubectl --kubeconfig $CONFIG create -f kibana-service.yaml
