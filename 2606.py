from collections import deque


def bfs(root):
    q = deque([root])
    visited = []
    while q:
        n = q.popleft()
        if n not in visited:
            visited.append(n)
            for item in graph[n]:
                q.append(item)
    return visited


if __name__ == '__main__':

    n = int(input())
    e = int(input())
    graph = [[] for i in range(n+1)]

    for i in range(e):
        str, end = map(int, input().split(" "))
        graph[str].append(end)
        graph[end].append(str)

    visited = bfs(1)
    print(len(visited)-1)
    print(visited)
    print(graph)