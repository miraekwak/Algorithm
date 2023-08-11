package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1699_제곱수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int[] dp = new int[N+1];
        for(int i=1; i<=N; i++) {
            dp[i] = i;
            for(int j=1; j*j <=i; j++) {
                if(dp[i] > dp[i-j*j] + 1){
                    dp[i] = dp[i-j*j] + 1;
                }
            }
        }
        System.out.println(dp[N]);
    }
}
