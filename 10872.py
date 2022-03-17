def f(n):
    if n == 1 or n == 0:
        return 1
    return f(n-1)*n


if __name__ == '__main__':
    N = int(input())
    print(f(N))