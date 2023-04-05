import sys
numbers = list(map(str, sys.stdin.readline()))
numbers.sort(reverse=True)
print(*numbers, sep="")
