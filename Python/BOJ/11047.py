if __name__ == '__main__':
    n, k = list(map(int, input().split(" ")))
    coin = []
    for i in range(n):
        coin.append(int(input()))

    count=0
    coin.sort(reverse=True)
    for c in coin:
        if k >= c:
            count += k//c
            k = k % c

    print(count)