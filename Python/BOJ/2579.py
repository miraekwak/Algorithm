if __name__ == '__main__':
    n = int(input())

    score = [0]
    step = [[0,0]]
    for _ in range(n):
        v = int(input())
        score.append(v)
        step.append([0, v])

    for i in range(2, n+1):
        # 안 밟음
        step[i][0] = step[i-1][1]

        # 밟음
        step[i][1] = max(step[i-2][0]+score[i-1], step[i-2][1]) + score[i]

    print(score)
    print(step)
    print(step[n][1])