if __name__ == '__main__':
    N = int(input())
    cnt = 0
    number = list(map(int, input().split()))

    for n in number:
        is_sosu = 1
        if n == 1:
            continue
        for i in range(2, n):
            if n % i == 0:
                is_sosu = 0
                break
        if is_sosu:
            cnt += 1
    print(cnt)