def dfs(time, numbers=[0]):
    if time == 0:
        print(*numbers[1:], sep=" ")
        return
    for i in range(1, N+1):
        if numbers[-1] <= i:
            arr = numbers.copy()
            arr.append(i)
            dfs(time-1, arr)


if __name__ == '__main__':
    N, M = map(int, input().split())
    dfs(M)