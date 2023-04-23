package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        long[][] dp = new long[N+1][2];
        dp[1][0] = 0;
        dp[1][1] = 1;
        for(int i=2; i<=N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }
        System.out.println(dp[N][0] + dp[N][1]);
    }
}
