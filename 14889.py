import sys
from itertools import permutations
N = int(sys.stdin.readline())
ability = [list(map(int, sys.stdin.readline().split())) for i in range(N)]
difference = sys.maxsize


def dfs(start=[], link=[], idx=0):
    global difference, ability
    if idx == N:
        total = 0
        for i, j in permutations(start, 2):
            total += ability[i][j]
        for i, j in permutations(link, 2):
            total -= ability[i][j]
        difference = min(difference, abs(total))
    if len(start) < N//2:
        dfs(start+[idx], link, idx+1)
    if len(link) < N//2:
        dfs(start, link+[idx], idx+1)


dfs()
print(difference)

