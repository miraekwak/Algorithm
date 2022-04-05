import sys
N = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))
# 0:+, 1:-, 2:*, 3://
a, b, c, d = map(int, sys.stdin.readline().split())
max_num = -sys.maxsize-1
min_num = sys.maxsize


def dfs(num, idx, add, sub, mul, div):
    global max_num, min_num
    if idx == N:
        max_num = max(max_num, num)
        min_num = min(min_num, num)
        return
    if add > 0:
        dfs(num+numbers[idx], idx+1, add-1, sub, mul, div)
    if sub > 0:
        dfs(num-numbers[idx], idx+1, add, sub-1, mul, div)
    if mul > 0:
        dfs(num*numbers[idx], idx+1, add, sub, mul-1, div)
    if div > 0:
        if num < 0 < numbers[idx]:
            dfs(-(abs(num)//numbers[idx]), idx + 1, add, sub, mul, div - 1)
        else:
            dfs(num//numbers[idx], idx+1, add, sub, mul, div-1)


dfs(numbers[0], 1, a, b, c, d)
print(max_num)
print(min_num)