package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Queue{
    int[] queue;
    int front=0;
    int back=0;
    public Queue(int N){
        queue = new int[N];
    }
    public void push(int x){
        queue[back] = x;
        back++;
    }
    public int pop() {
        if(empty()==1) {
            return -1;
        }
        int temp = queue[front];
        front++;
        return temp;
    }
    public int size() {
        return back - front;
    }
    public int empty() {
        return size() == 0 ? 1 : 0;
    }
    public int front(){
        return size() > 0 ? queue[front] : -1;
    }
    public int back(){
        return size() > 0 ? queue[back-1] : -1;
    }

}

public class boj_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        Queue queue = new Queue(N);
        StringBuilder sb = new StringBuilder();
        while (N-- > 0){
            String[] orders = br.readLine().split(" ");
            switch (orders[0]) {
                case "push":
                    queue.push(Integer.valueOf(orders[1]));
                    break;
                case "pop":
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.empty()).append("\n");
                    break;
                case "front":
                    sb.append(queue.front()).append("\n");
                    break;
                case "back":
                    sb.append(queue.back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

}
