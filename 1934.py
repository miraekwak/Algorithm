import sys
n = int(sys.stdin.readline())
for i in range(n):
    a, b = map(int, sys.stdin.readline().split())
    idx = 2
    total = 1
    while a >= idx and b >= idx:
        if a % idx == 0 and b % idx == 0:
            total *= idx
            a = a//idx
            b = b//idx
        else:
            idx += 1
    print(total*a*b)