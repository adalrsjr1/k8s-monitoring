#!/bin/sh

CONFIG=../admin.conf

kubectl --kubeconfig $CONFIG apply -f https://raw.githubusercontent.com/kubernetes/dashboard/master/src/deploy/alternative/kubernetes-dashboard.yaml

kubectl --kubeconfig $CONFIG apply -f dashboard-admin.yaml
