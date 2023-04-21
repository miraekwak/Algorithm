package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]*2) % 10007;
        }
        System.out.println(dp[N]);
    }
}
