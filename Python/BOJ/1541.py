if __name__ == '__main__':
    literal = input().split('-')

    sum = 0
    for i in literal[0].split('+'):
        sum += int(i)

    for i in literal[1:]:
        for j in i.split('+'):
            sum -= int(j)

    print(sum)