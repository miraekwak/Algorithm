import sys
n = int(sys.stdin.readline())
fct = 1
for i in range(n, 1, -1):
    fct *= i
cnt = 0
fct = str(fct)
for i in range(len(fct)-1, -1, -1):
    if fct[i] == "0":
        cnt += 1
    else:
        break
print(cnt)