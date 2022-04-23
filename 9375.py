import sys
from itertools import combinations
from functools import reduce
t = int(sys.stdin.readline())
for _ in range(t):
    closet = dict()
    n = int(sys.stdin.readline())
    for i in range(n):
        name, kind = map(str, sys.stdin.readline().split())
        if kind in closet.keys():
            closet[kind] += 1
        else:
            closet[kind] = 1
    total = 1
    for key in closet.keys():
        total *= (closet[key] + 1)
    print(total - 1)