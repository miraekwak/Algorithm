if __name__ == '__main__':
    n = int(input())

    rgblist= []
    for i in range(n):
        rgblist.append(list(map(int, input().split(" "))))

    for i in range(1, n):
        # red house
        rgblist[i][0] = min(rgblist[i-1][1], rgblist[i-1][2]) + rgblist[i][0]

        #green house
        rgblist[i][1] = min(rgblist[i-1][0], rgblist[i-1][2]) + rgblist[i][1]

        # blue house
        rgblist[i][2] = min(rgblist[i-1][0], rgblist[i-1][1]) + rgblist[i][2]

    print(min(rgblist[n-1]))