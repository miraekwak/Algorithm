if __name__ == '__main__':
    A, B, C = list(map(int, input().split(" ")))

    if B >= C:
        print(-1)
    else:
        print(int(A/(C-B)+1))

