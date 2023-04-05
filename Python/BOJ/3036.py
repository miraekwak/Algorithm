import sys
import math
n = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))
standard = numbers[0]
for i in range(1, n):
    max_ = math.gcd(standard, numbers[i])
    min_multiple = (standard//max_) * (numbers[i]//max_) * max_
    print("%d/%d" %(min_multiple//numbers[i], min_multiple//standard))