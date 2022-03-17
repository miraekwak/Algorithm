def fib(f1, f2, n):
    if n == 1:
        return f2
    return fib(f2, f1+f2, n-1)


if __name__ == '__main__':
    n = int(input())
    if n == 0 or n == 1:
        print(n)
    else:
        print(fib(0, 1, n))
