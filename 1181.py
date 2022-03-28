import sys
N = int(input())
words = []
for _ in range(N):
    words.append(sys.stdin.readline().rstrip())
words = list(set(words))
words.sort()
words.sort(key= lambda x: len(x))
for word in words:
    print(word)