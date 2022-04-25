import sys
n, k = map(int, sys.stdin.readline().split())
numbers = [0]*(n+1)
for idx, val in enumerate(list(map(int, sys.stdin.readline().split()))):
    numbers[idx+1] = val + numbers[idx]
interval = [0]*(n-k+1)
for i in range(1, n-k+2):
    interval[i-1] = numbers[i+k-1] - numbers[i-1]
print(max(interval))