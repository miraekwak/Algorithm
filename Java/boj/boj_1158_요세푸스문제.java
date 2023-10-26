package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_1158_요세푸스문제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] order = br.readLine().split(" ");
        int N = Integer.valueOf(order[0]);
        int K = Integer.valueOf(order[1]);

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            queue.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int count = 1;
        while(queue.size() != 1){
            if(count == K) {
                sb.append(queue.poll()).append(", ");
                count = 1;
            }
            else {
                queue.offer(queue.poll());
                count++;
            }
        }
        sb.append(queue.poll()).append(">");
        System.out.println(sb);
    }
}
