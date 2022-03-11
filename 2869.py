import math

if __name__ == '__main__':
    A, B, V = map(int, input().split())
    print(int(1 + math.ceil((V-A)/(A-B))))
