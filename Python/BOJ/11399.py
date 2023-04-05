import sys

if __name__ == '__main__':

    person = int(input())
    time = list(map(int, sys.stdin.readline().split(" ")))

    time.sort()

    timelist = []
    total_time = 0
    for t in time:
        total_time += t
        timelist.append(total_time)

    print(sum(timelist))