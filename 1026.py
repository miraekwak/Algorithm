if __name__ == '__main__':

    n = int(input())

    a = list(map(int, input().split()))
    b = list(map(int, input().split()))

    sortA = sorted(a, reverse=True)
    sortB = sorted(b)

    total = 0
    for i in range(n):
        total += sortA[i]*sortB[i]

    print(total)