package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Deque {
    private LinkedList<Integer> list;
    public Deque(){
        list = new LinkedList<>();
    }
    public void push_front(int x) {
        list.addFirst(x);
    }
    public void push_back(int x) {
        list.addLast(x);
    }
    public int pop_front() {
        return empty() == 1 ? -1 : list.removeFirst();
    }
    public int pop_back() {
        return empty() == 1 ? -1 : list.removeLast();
    }
    public int size() {
        return list.size();
    }
    public int empty() {
        return list.isEmpty() ? 1 : 0;
    }
    public int front() {
        return empty() == 1 ? -1 : list.getFirst();
    }
    public int back() {
        return empty() == 1 ? -1 : list.getLast();
    }

}

public class boj_10866_덱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque deque = new Deque();
        while (N-- > 0) {
            String[] order = br.readLine().split(" ");
            switch (order[0]) {
                case "push_front":
                    deque.push_front(Integer.valueOf(order[1]));
                    break;
                case "push_back":
                    deque.push_back(Integer.valueOf(order[1]));
                    break;
                case "pop_front":
                    sb.append(deque.pop_front()).append("\n");
                    break;
                case "pop_back":
                    sb.append(deque.pop_back()).append("\n");
                    break;
                case "size":
                    sb.append(deque.size()).append("\n");
                    break;
                case "empty":
                    sb.append(deque.empty()).append("\n");
                    break;
                case "front":
                    sb.append(deque.front()).append("\n");
                    break;
                case "back":
                    sb.append(deque.back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
