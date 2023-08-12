package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_17298_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] arr = new int[line.length];
        for(int i=0; i< line.length; i++) {
            arr[i] = Integer.valueOf(line[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i< arr.length; i++) {
            while(!stack.empty() && arr[i] > arr[stack.peek()]) {
                int idx = stack.pop();
                arr[idx] = arr[i];
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int idx = stack.pop();
            arr[idx] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int num: arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
