def dfs(time, numbers=[0]):
    if time == 0:
        print(*numbers[1:], sep=" ")
        return
    for i in range(1, N+1):
        if i not in numbers and numbers[-1] < i:
            new_numbers = numbers.copy()
            new_numbers.append(i)
            dfs(time-1, new_numbers)


if __name__ == '__main__':
    N, M = map(int, input().split())
    dfs(M)