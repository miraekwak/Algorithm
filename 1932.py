import copy

if __name__ == '__main__':
    n = int(input())

    pyramid = []
    for i in range(n):
        pyramid.append(list(map(int, input().split(" "))))

    dp = copy.deepcopy(pyramid)
    for i in range(n-1):
        for j in range(len(pyramid[i])):
            dp[i + 1][j] = max(dp[i + 1][j], pyramid[i + 1][j] + dp[i][j])
            dp[i + 1][j+1] = max(dp[i+1][j+1], pyramid[i + 1][j+1] + dp[i][j])
    print(max(dp[n-1]))