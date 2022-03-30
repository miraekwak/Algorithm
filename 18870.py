import sys
N = int(input())
numbers = list(map(int, sys.stdin.readline().split()))
# code 1
# order_numbers = []
# for i in range(N):
#     order_numbers.append([numbers[i], i])
# order_numbers.sort(key=lambda x: x[0])
# press = [0]*N
# press[0] = 0
# prev = 0
# for i in range(1, N):
#     num, order = order_numbers[i]
#     if order_numbers[i-1][0] == num:
#         press[order] = prev
#     else:
#         prev += 1
#         press[order] = prev
# print(*press, sep=' ')

# code 2
order_numbers = sorted(list(set(numbers)))
dic = {order_numbers[i] : i for i in range(len(order_numbers))}
for num in numbers:
    print(dic[num], end=' ')