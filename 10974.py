from itertools import permutations
if __name__ == '__main__':
    n = int(input())

    lst = []
    for i in range(1, n+1):
        lst.append(i)

    permutation = list(permutations(lst, n))
    for i in permutation:
        for j in i:
            print(j, end=' ')
        print()