def coloring(color, str_h, str_w):
    cnt = 0
    for h in range(str_h, str_h+8):
        for w in range(str_w, str_w+8):
            if h % 2 == 0:
                if w % 2 == 0:
                    if color != board[h][w]:
                        cnt += 1
                else:
                    if color == board[h][w]:
                        cnt += 1
            else:
                if w % 2 == 0:
                    if color == board[h][w]:
                        cnt += 1
                else:
                    if color != board[h][w]:
                        cnt += 1
    return cnt


def divide(color):
    min_num = N*M
    for str_h in range(0, N-8+1):
        for str_w in range(0, M-8+1):
            min_num = min(min_num, coloring(color, str_h, str_w))
    return min_num


if __name__ == '__main__':
    N, M = map(int, input().split())

    board = []
    for i in range(N):
        board.append(list(str(input())))

    print(min(divide('B'), divide('W')))