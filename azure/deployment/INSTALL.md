# Installing Kubernetes on Azure

`kubeadm init --apiserver-cert-extra-sans=<vm dns> --token-ttl <duration - 0 to no expires>`

Follow the steps on <https://kubernetes.io/docs/setup/independent/create-cluster-kubeadm/> and install Weave Network as specified in the link above.

When finished, to copy the `~/.kube/conf` to you machine and change the service name for the `<vm dns>` set on `kubeadm init`.

Finally, open the ports `6443` and `30000-31000` on azure master.
