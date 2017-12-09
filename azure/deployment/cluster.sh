#!/bin/bash

create_vm() {
  local arg resource_group location name user size image ssh no_wait

  while getopts ':g:l:u:n:s:i:h:w:' arg
  do
    case ${arg} in
      g) resource_group=${OPTARG};;
      l) location=${OPTARG};;
      n) name=${OPTARG};;
      s) size=${OPTARG};;
      i) image=${OPTARG};;
      u) user=${OPTARG};;
      h) ssh=${OPTARG};;
      w) no_wait=${OPTARG};;
      *) echo "illegal parameter ${OPTARG}" 
         return 1 # illegal option
    esac
  done

  if [ -z ${no_wait+x} ]; then # checking is no_wait is set
    echo "waiting"
    echo "creating $resource_group $location $name $image $user $ssh $size $no_wait"
  else
    echo "no waiting"
    echo "creating $resource_group $location $name $image $user $ssh $size $no_wait"
  fi

  # az vm option --resource-group $resource_group --location $location \
  # --name $name --image $image --admin-username $user --ssh-key-value $ssh \
  # --size $size
}

manage_vm() {
  local option resource_group name no_wait

  while getopts ':o:g:n:w:' arg
  do
    case ${arg} in
      g) resource_group=${OPTARG};;
      n) name=${OPTARG};;
      w) no_wait=${OPTARG};;
      o) option=${OPTARG};;
      *) echo "illegal parameter ${OPTARG}"
         return 1
    esac
  done

  if [ -z ${no_wait+x} ]; then # checking if no_wait is set
    echo waiting
    if [ $option == "start" ]; then
      echo "$option $resource_group $name $no_wait"
    elif [ $option == "stop" ]; then
      echo "$option $resource_group $name $no_wait"
    else
      echo "option -o ivalid"
      return 1
    fi
  else
    echo no waiting
    if [ $option == "start" ]; then
      echo "$option $resource_group $name $no_wait"
    elif [ $option == "stop" ]; then
      echo "$option $resource_group $name $no_wait"
    else
      echo "option -o ivalid"
      return 1
    fi
  fi

# az vm start -g group -n name --no-wait
# az vm deallocate -g group -n name --no-wait
}

run_command_on_vm() {
  local action ssh_id target

  while getopts ':a:i:t:' arg
  do
    case ${arg} in
      a) action=${OPTARG};;
      i) ssh_id=${OPTARG};;
      t) target=${OPTARG};;
      *) echo "illegal parameter ${OPTARG}"
         return 1
    esac
  done

  # ssh -i $ssh_id "$action"
}

run_command_on_vm "$@"
