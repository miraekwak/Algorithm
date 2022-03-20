def hanoi(n, from_pos, to_pos, mid_pos):
    if n == 1:
        print(from_pos, to_pos)
        return
    hanoi(n-1, from_pos, mid_pos, to_pos)
    print(from_pos, to_pos)
    hanoi(n-1, mid_pos, to_pos, from_pos)


if __name__ == '__main__':
    N = int(input())
    print(2**N-1)
    hanoi(N, 1, 3, 2)