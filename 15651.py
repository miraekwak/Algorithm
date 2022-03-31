def dfs(time, numbers=[]):
    if time == 0:
        print(*numbers, sep=" ")
        return
    for i in range(1, N+1):
        new_numbers = numbers.copy()
        new_numbers.append(i)
        dfs(time-1, new_numbers)


if __name__ == '__main__':
    N, M = map(int, input().split())
    dfs(M)