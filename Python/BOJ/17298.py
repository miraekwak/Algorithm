import sys
n = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))
answer = [-1]*n
stack = []
for i in range(n):
    while stack and stack[-1][0] < numbers[i]:
        v, idx = stack.pop()
        answer[idx] = numbers[i]
    stack.append([numbers[i], i])
print(*answer)