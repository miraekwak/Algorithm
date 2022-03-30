from itertools import permutations
if __name__ == '__main__':
    N, M = map(int, input().split())
    numbers = [i for i in range(1, N+1)]
    for item in permutations(numbers, M):
        print(*item, sep=" ")