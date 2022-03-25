import sys
def merge_sort(numbers):
    if len(numbers) <= 1:
        return numbers
    mid = len(numbers)//2
    left = merge_sort(numbers[:mid])
    right = merge_sort(numbers[mid:])

    l, r, i = 0, 0, 0
    while l < len(left) and r < len(right):
        if left[l] >= right[r]:
            numbers[i] = right[r]
            r += 1
        else:
            numbers[i] = left[l]
            l += 1
        i += 1
    if r == len(right):
        while l < len(left):
            numbers[i] = left[l]
            i += 1
            l += 1
    elif l == len(left):
        while r < len(right):
            numbers[i] = right[r]
            i += 1
            r += 1

    return numbers


if __name__ == '__main__':
    N = int(input())

    numbers = []
    for _ in range(N):
        numbers.append(int(sys.stdin.readline()))
    print(*merge_sort(numbers), sep="\n")
    # numbers.sort()
    # print(*numbers, sep="\n")