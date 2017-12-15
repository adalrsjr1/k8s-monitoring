#!/bin/bash

pdftk graph-barabasi-N-1.pdf Performance*.pdf cat output graphN-1-micro_benchmark-analysis_planning.pdf
pdftk graph-barabasi-N-1.pdf Heuristic.pdf SMTSolver.pdf cat output graphN-1-hosts_saved.pdf
