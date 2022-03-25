import sys
from collections import Counter

N = int(input())
numbers = []
for _ in range(N):
    numbers.append(int(sys.stdin.readline()))
numbers.sort()
print(round(sum(numbers)/N))
print(numbers[N//2])
# nums = Counter(numbers).most_common()
# if len(nums) > 1 and nums[0][1] == nums[1][1]:
#     print(nums[1][0])
# else:
#     print(nums[0][0])
num = list(set(numbers))
maxNums = []
max_count = 0
for i in num:
    if max_count < numbers.count(i):
        maxNums = []
        maxNums.append(i)
        max_count = numbers.count(i)
    elif max_count == numbers.count(i):
        maxNums.append(i)
if len(maxNums) > 1:
    maxNums.sort()
    print(maxNums[1])
else:
    print(maxNums[0])
print(numbers[-1]-numbers[0])