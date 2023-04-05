if __name__ == '__main__':
    N = int(input())
    persons = []
    for _ in range(N):
        persons.append(tuple(map(int, input().split())))

    sizes = [1] * N
    for i in range(N-1):
        for j in range(i+1, N):
            w1, h1 = persons[i]
            w2, h2 = persons[j]
            if w1 > w2 and h1 > h2:
                sizes[j] += 1
            elif w2 > w1 and h2 > h1:
                sizes[i] += 1

    print(*sizes, sep=' ')
