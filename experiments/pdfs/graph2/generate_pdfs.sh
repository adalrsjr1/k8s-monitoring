#!/bin/bash

pdftk graph-barabasi-2.pdf Performance*.pdf cat output graph2-micro_benchmark-analysis_planning.pdf
pdftk graph-barabasi-2.pdf Heuristic.pdf SMTSolver.pdf SMT.pdf cat output graph2-hosts_saved.pdf
