from itertools import combinations

if __name__ == '__main__':
    N, M = map(int, input().split())
    cards = list(map(int, input().split()))
    nearby_sum = 0
    for card in list(combinations(cards, 3)):
        s = sum(card)
        if M >= s > nearby_sum:
            nearby_sum = s

    print(nearby_sum)