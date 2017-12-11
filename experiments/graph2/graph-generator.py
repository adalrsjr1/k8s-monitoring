#!/usr/bin/env python

import networkx as nx
import matplotlib.pyplot as plt

g = nx.barabasi_albert_graph(10,2,31)
nx.draw(g)
plt.savefig('graph-barabasi-2.pdf')
plt.show()

