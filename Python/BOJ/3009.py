if __name__ == '__main__':
    # points = []
    # for _ in range(3):
    #     x, y = map(int, input().split())
    #     points.append((x, y))
    #
    # for x, y in points:
    #     for x1, y1 in points:
    #         if (x, y1) not in points:
    #             print(x, y1)
    #             break
    #

    x_num = []
    y_num = []

    for _ in range(3):
        x, y = map(int, input().split())
        x_num.append(x)
        y_num.append(y)

    for i in range(3):
        if x_num.count(x_num[i]) == 1:
            x = x_num[i]
        if y_num.count(y_num[i]) == 1:
            y = y_num[i]
    print(x, y)




