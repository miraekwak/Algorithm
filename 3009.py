if __name__ == '__main__':
    points = []
    for _ in range(3):
        x, y = map(int, input().split())
        points.append((x, y))

    for x, y in points:
        for x1, y1 in points:
            if (x, y1) not in points:
                print(x, y1)
                break


