if __name__ == '__main__':
    num = int(input())
    has_num = False
    for i in range(1, num, 1):
        dst_sum = i
        dst_num = i
        while dst_num >= 10:
            dst_sum += dst_num % 10
            dst_num //= 10
        dst_sum += dst_num
        if dst_sum == num:
            print(i)
            has_num = True
            break
    if not has_num:
        print(0)
