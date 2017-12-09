#Sam Bayless, 2017 <https://github.com/sambayless>
#License: CC0 and/or MIT

from z3 import *
import random
import json
random.seed(0)

#create a new Z3 solver instance.
#s =  Optimize() # Use Optimize() because we are doing optimization below. If not using Z3 for optimization, this should instead be s= Solver()
s = Solver()

class Job:

    def __init__(self, name,required_cpu,required_memory):
        self.name = name
        # IntVal(python_value) creates a Z3 constant integer value, as opposed to a Z3 variable.
        # In some cases, Z3 will implicitly convert a python value (eg, an int) into a Z3 constant,
        # but in some cases it does not, so it helps to avoid bugs if you always explicitly create python constants
        # using IntVal, BoolVal, or RealVal
        # If you were instead creating Z3 variables (rather than Z3 constants), you would use Int(), Real(), or Bool()
        self.required_memory = IntVal(required_memory)
        self.required_cpu = IntVal(required_cpu)

class Node:
    def __init__(self, name, available_cpu,available_memory):
        self.name = name
        self.available_cpu = IntVal(available_cpu)
        self.available_memory = IntVal(available_memory)

    def __hash__(self):
        return hash(self.name)

    def __eq__(self, other):
        return isinstance(other, Node) and self.name == other.name

expected_runtimes=dict()

data = \
  json.load(open('/home/adalrsjr1/Code/ibm-stack/storage-clients/model-manager/src/main/resources/z3/allocz3.json'))


def dictToNode(d):
    return Node(d['name'], d['cpu'], d['memory'])

def dictToJob(d):
    return Job(d['name'], d['cpu'], d['memory'])

affinities = dict()
jobs = []
nodes = set()
for affinity in data:
    j1 = dictToJob(affinity['source'])
    j2 = dictToJob(affinity['target'])
    jobs.append(j1)
    jobs.append(j2)
    assert (j1 != j2)
    affinities[(j1,j2)] = affinity['affinity']
    n1 = dictToNode(affinity['source']['host'])
    n2 = dictToNode(affinity['target']['host'])

    if n1 not in nodes:
        nodes.add(n1)
    if n2 not in nodes:
        nodes.add(n2)

#for i in range(10):
#    # generate some nodes with random settings.
#    # In practice, these should be replaced with the real values for your network
#    cpu = random.randint(1,2000)
#    memory = 4096
#    nodes.append(Node("Node_%d"%(i),cpu,memory))
#
##create some jobs
#jobs=[]
#for j in range(10):
#    # creating fake, random expected runtime predictions for each job
#    # you should read these out of the csv instead
#    cpu_requirement = random.randint(1,500)
#    memory_requirement = random.randint(1,1024)
#    jobs.append(Job("j%d"%(j), cpu_requirement, memory_requirement))
#
# create some job affinities.
#affinities = dict()
#for a in range(10):
#    #pick two jobs at random and create an affinity between them
#    j1,j2 = random.sample(jobs,2)
#    assert(j1!=j2)
#    affinity = IntVal(random.randint(0,10)) # the higher the affinity score, the more we want to place these two jobs
#    # on the same node
#    affinities[(j1,j2)]=affinity


#The following constraints force Z3 to find a valid placement of jobs to nodes (but do not yet attempt to maximize
# affinity)
job_placements = dict()
for j in jobs:
    job_placements [j]=dict()

node_placements = dict()
for n in nodes:
    node_placements [n]=[]


for j in jobs:
    #each job has to be placed on exactly one node

    node_choices = []
    node_choices_pb = []
    for n in nodes:
         # For each job node pair, create a Boolean variable in Z3.
         # If that Bool is assigned true, then we interpret it to mean that Z3 placed this job on this node.
         # Note: All Z3 variables (not constants) must be given a unique string name, which must be different from
         # any other Z3 variables. In this case, this Bool variable is given the name "place_%s_on_%s"%(j.name,n.name)
         p = Bool("place_%s_on_%s"%(j.name,n.name));
         node_choices.append(p)
         node_choices_pb.append((p,1))
         node_placements[n].append((p,j))
         job_placements[j][n] =p

    #Assert that each job is placed on _exactly_ one node
    # there are several encodings that can achieve this constraint, and you may need to play around with a few to find
    # the one that has the best performance. Below I am using a Pseudo-Boolean encoding. But arithmetic
    # encodings are also possible (commented out below)
    #s.add(z3.PbEq(node_choices_pb, 1)) # this not work for just one node
    s.add(Sum([If(b, 1, 0) for b in node_choices]) == 1)


# assert that, for each node, the sum of the jobs placed on that node do not exceed the available CPU
# this is 'hard' constraint - Z3 will refuse to find a solution at all, if there does not exist a placement that respects
# these constraints
for n in nodes:
    placements =  node_placements[n]
    sum_used_cpu = Sum([If(p,j.required_cpu,0) for p,j in placements])
    s.add(sum_used_cpu<=n.available_cpu)
    n.sum_used_cpu = sum_used_cpu

# assert that, for each node, the sum of the jobs placed on that node do not exceed the available memory
for n in nodes:
    placements =  node_placements[n]
    sum_used_memory = Sum([If(p,j.required_memory,0) for p,j in placements])
    s.add(sum_used_memory<=n.available_memory)
    n.sum_used_memory = sum_used_memory



# maximize the sum total affinity.
# there are other possible ways we could set up this objective function for the affinity score.
affinity_score = IntVal(0)


for (j1, j2),val in affinities.items():
    both_jobs_on_same_node=[]
    for n in nodes:
        both_jobs_on_this_node = And(job_placements[j1][n],job_placements[j2][n])
        both_jobs_on_same_node.append(both_jobs_on_this_node)

    # if both jobs are placed by Z3 on the same node, then add their affinity value to the affinity score
    affinity_score = If(Or(both_jobs_on_same_node),affinity_score+val,affinity_score)

#s.maximize(affinity_score ) # The objective function should be an integer (or real) that Z3 will minimize or maximize.
s.add(affinity_score > 0)

#print("Solving...")
r =  s.check()
#print(str(r))
if r==sat: # attempt to solve the instance, and return True if it could be solved
    #print("Found valid placement")
    m = s.model() # the model contains the actual assignments found by Z3 for each variable

    # print out the objective function we are minimizing
    # m.evaluate(x,True) extracts the sat solver's solution from the model
    # and then .as_long() converts that solution into a python long that we can print
    a = m.evaluate(affinity_score,model_completion=True).as_long()
    #print("Affinity score is %d" % (a))
    assert(a>=0)

    # print out the allocation found by Z3
    print("[")
    for j in jobs:
        placements = job_placements[j]
        n_found=0
        for n,p in placements.items():
            val = m.evaluate(p, True)
            if val:
                assert(n_found==0)
                n_found+=1
                #print("Placed job %s on node %s"%(j.name,n.name))
                print('{"job":"%s", "host":"%s"},'%(j.name, n.name))

        assert(n_found==1) # sanity check: each job should be placed on exactly one node
    print("{}]") 
    #sanity checking the cpu/ram requirements
    for n in nodes:
        cpu_usage = m.evaluate(n.sum_used_cpu,True).as_long()
        available_cpu = m.evaluate(n.available_cpu,True).as_long()
        assert(cpu_usage<=available_cpu)

        memory_usage = m.evaluate(n.sum_used_memory, True).as_long()
        available_memory = m.evaluate(n.available_memory, True).as_long()
        assert (memory_usage <= available_memory)


else:
    #print("Could not find a valid placement")
    print("[]")
