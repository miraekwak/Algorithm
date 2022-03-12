if __name__ == '__main__':
    s = 123456*2+1
    prime = [True]*s
    for i in range(2, int(s**0.5)+1):
        if prime[i]:
            for j in range(i*2, s, i):
                prime[j] = False
    while True:
        N = int(input())
        if N == 0:
            break
        cnt = 0
        for number in range(N+1, 2*N+1):
            if prime[number]:
                cnt += 1
        print(cnt)