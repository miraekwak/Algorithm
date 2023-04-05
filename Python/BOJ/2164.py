import sys
from collections import deque
n = int(sys.stdin.readline())
d = deque([i for i in range(1, n+1)])
while len(d) > 1:
    d.popleft()
    temp = d.popleft()
    d.append(temp)
print(d.pop())