def is_exist(q):
    for i in range(q):
        if queen[q] == queen[i] or abs(queen[q]-queen[i]) == q - i:
            return False
    return True


def dfs(q):
    global result
    if q == N:
        result += 1
    else:
        for i in range(N):
            queen[q] = i
            if is_exist(q):
                dfs(q+1)


if __name__ == '__main__':
    N = int(input())
    queen = [0]*N
    result = 0
    dfs(0)
    print(result)