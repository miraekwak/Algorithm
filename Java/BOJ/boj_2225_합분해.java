package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2225_합분해 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(numbers[0]);
        int K = Integer.parseInt(numbers[1]);
        int mod = 1_000_000_000;
        int[][] dp = new int[K+1][N+1];
        for(int i=0; i<=N; i++) {
            dp[1][i] = 1;
        }
        for(int i=0; i<=K; i++) {
            dp[i][0] = 1;
        }
        for(int i=2; i<=K; i++) {
            for(int j=1; j<=N; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % mod;
            }
        }
        System.out.println(dp[K][N]);
    }
}
