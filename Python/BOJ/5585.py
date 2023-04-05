if __name__ == '__main__':

    coin = [500, 100, 50, 10, 5, 1]

    money = 1000 - int(input())

    count = 0
    for c in coin:
        count += money//c
        money = money % c

    print(count)