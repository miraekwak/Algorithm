if __name__ == '__main__':
    time = int(input())

    for i in range(time):
        H, W, N = map(int, input().split())
        if N % H == 0:
            print(H, '{0:02d}'.format(N//H), sep='')
        else:
            print(N % H, '{0:02d}'.format(N//H+1), sep='')

