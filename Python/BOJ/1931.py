
if __name__ == '__main__':

    n = int(input())
    timelist = []
    for i in range(n):
        s, e = map(int, input().split(" "))
        timelist.append((s,e))

    timelist.sort(key=lambda x: (x[1], x[0]))

    last = 0
    count = 0
    for s, e in timelist:
        if s >= last:
            count += 1
            last = e

    print(count)