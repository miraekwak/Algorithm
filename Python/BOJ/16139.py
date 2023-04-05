import sys
s = sys.stdin.readline().rstrip()
n = int(sys.stdin.readline())
alpha = [[0]*26 for _ in range(len(s))]
alpha[0][ord(s[0])-97] = 1
for i in range(1, len(s)):
    alpha[i][ord(s[i])-97] += 1
    for j in range(26):
        alpha[i][j] += alpha[i-1][j]
for _ in range(n):
    a, i, j = map(str, sys.stdin.readline().rstrip().split())
    i, j = int(i), int(j)
    if i > 0:
        result = alpha[j][ord(a)-97] - alpha[i-1][ord(a)-97]
    else:
        result = alpha[j][ord(a)-97]
    print(result)