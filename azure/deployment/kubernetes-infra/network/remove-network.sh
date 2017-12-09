#!/bin/bash

CONFIG=$1

export kubever=$(kubectl version | base64 | tr -d '\n')
kubectl --kubeconfig=$CONFIG delete -f "https://cloud.weave.works/k8s/net?k8s-version=$kubever"
