#!/bin/bash

echo '' > results.out
for f in *.txt; do
  ./parse.py $f /orders avg >> results.out
done;
