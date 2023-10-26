package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class boj_14002_가장긴증가하는부분수열4 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(i -> Integer.valueOf(i))
                .toArray();
        int[] dp = new int[N];
        int[] idx = new int[N];
        for(int i=0; i<N; i++) {
            idx[i] = -1;
        }
        for(int i=0; i<numbers.length; i++) {
            dp[i] = 1;
            for(int j=i-1; j>=0; j--) {
                if(numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    idx[i] = dp[i] > dp[j]+1 ? idx[i] : j;
                }
            }
        }
        int max_value = Arrays.stream(dp).max().getAsInt();
        System.out.println(max_value);
        int max_idx=0;
        for(int i=1; i<dp.length; i++) {
            if(dp[i] == max_value) {
                max_idx = i;
                break;
            }
        }
        Stack stack = new Stack<>();
        while(max_idx > -1) {
            stack.push(numbers[max_idx]);
            max_idx = idx[max_idx];
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()+" ");
        }
        System.out.println(sb);
    }
}
