import sys
t = int(sys.stdin.readline())
for i in range(t):
    string = list(sys.stdin.readline().rstrip())
    s = []
    for j in string:
        if j == '(':
            s.append(j)
        elif j == ')':
            if len(s) == 0:
                s.append(j)
                break
            s.pop()
    if len(s) == 0:
        print('YES')
    else:
        print('NO')