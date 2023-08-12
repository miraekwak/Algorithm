package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1874_스택수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bf.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        while(N-- > 0){
            int item = Integer.valueOf(bf.readLine());
            while(item >= count) {
                stack.push(count);
                sb.append("+\n");
                count++;
            }
            if(item == stack.peek()){
                stack.pop();
                sb.append("-\n");
            }
            else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }
}
