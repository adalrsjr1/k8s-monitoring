#!/bin/sh

CONFIG=$1

kubectl --kubeconfig=$CONFIG delete -f https://raw.githubusercontent.com/kubernetes/dashboard/master/src/deploy/alternative/kubernetes-dashboard.yaml

kubectl --kubeconfig=$CONFIG delete -f dashboard-admin.yaml
