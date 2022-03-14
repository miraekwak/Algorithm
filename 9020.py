if __name__ == '__main__':
    N = int(input())
    s = 10001
    prime = [True] * s
    prime[0] = False
    prime[1] = False
    for i in range(2, int(s**0.5)+1):
        if prime[i]:
            for j in range(i*2, s, i):
                prime[j] = False

    for _ in range(N):
        n = int(input())
        mid = n//2
        for i in range(mid, 1, -1):
            if prime[i] and prime[n-i]:
                print(i, n-i)
                break
