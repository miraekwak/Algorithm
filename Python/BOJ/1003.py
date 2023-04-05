
if __name__ == '__main__':
    inf = 1e9
    k = int(input())
    number = []
    for i in range(k):
        number.append(int(input()))
    zero = [1,0]
    one = [0,1]

    for item in number:
        length = len(zero)
        if length <= item:
            for i in range(length, item+1):
                zero.append(zero[i-1]+zero[i-2])
                one.append(one[i-1]+one[i-2])
        print(zero[item], one[item])
