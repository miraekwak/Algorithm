import sys
from collections import deque
n, k = map(int, sys.stdin.readline().split())
circular_q = deque([i for i in range(1, n+1)])
answer = []
print("<", end="")
while len(circular_q) > 1:
    for i in range(1, k):
        temp = circular_q.popleft()
        circular_q.append(temp)
    print(circular_q.popleft(), end=", ")
print(circular_q.pop(), end=">")