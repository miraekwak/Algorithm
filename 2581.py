if __name__ == '__main__':
    M = int(input())
    N = int(input())
    prime = []
    for number in range(M, N+1):
        is_sosu = 1
        if number == 1:
            continue
        for i in range(2, number):
            if number % i == 0:
                is_sosu = 0
                break
        if is_sosu:
            prime.append(number)
    if not prime:
        print(-1)
    else:
        print(sum(prime))
        print(min(prime))