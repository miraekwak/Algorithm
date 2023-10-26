package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_17299_오등큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        String[] line = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.valueOf(line[i]);
        }

        int[] count = new int[Arrays.stream(arr).max().getAsInt()+1];
        for(int num: arr) {
            count[num] += 1;
        }

        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<N; i++) {
            while(!stack.empty() && count[arr[i]] > count[arr[stack.peek()]]) {
                arr[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        while(!stack.empty()){
            arr[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int ngf: arr){
            sb.append(ngf).append(' ');
        }
        System.out.println(sb);
    }
}
