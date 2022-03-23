if __name__ == '__main__':
    N = int(input())
    numbers = []
    for i in range(N):
        numbers.append(int(input()))

    numbers.sort()
    print(*numbers, sep="\n")