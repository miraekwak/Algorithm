
if __name__ == '__main__':

    n = int(input())

    count = 0
    while True:
        if n % 5 == 0:
            count += n // 5
            print(count)
            break
        n -= 3
        count += 1
        if n < 0:
            print("-1")
            break

