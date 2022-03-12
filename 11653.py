if __name__ == '__main__':
    N = int(input())
    i = 2
    while N > 1:
        if N % i == 0:
            print(i)
            N //= i
        else:
            i += 1

    # for i in range(2, int(N**0.5)+1):
    #     while N % i == 0:
    #         print(i)
    #         N //= i
    # if N > 1:
    #     print(N)