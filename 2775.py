if __name__ == '__main__':
    test = int(input())

    for _ in range(test):
        K = int(input())
        N = int(input())
        floor = [i for i in range(1, N+1)]
        for k in range(K):
            for n in range(1, N):
                floor[n] += floor[n-1]
        print(floor[N-1])

