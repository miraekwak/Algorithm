if __name__ == '__main__':

    t = int(input())
    n_list = []
    for i in range(t):
        n_list.append(int(input()))

    dp = [0 for _ in range(max(n_list)+1)]

    for i in range(1, len(dp)):
        if i-1 == 0 or i-2 == 0 or i-3 == 0:
            dp[i] += 1
        if i-1 > 0:
            dp[i] += dp[i-1]
        if i-2 > 0:
            dp[i] += dp[i-2]
        if i-3 > 0:
            dp[i] += dp[i-3]

    for item in n_list:
        print(dp[item])