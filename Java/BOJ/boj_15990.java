package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_15990 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        long[][] dp = new long[100_001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for(int i = 4; i < dp.length; i++) {
            dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % 1_000_000_009;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % 1_000_000_009;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % 1_000_000_009;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            sb.append((dp[num][1]+dp[num][2]+dp[num][3])% 1_000_000_009+"\n");
        }
        System.out.println(sb);
    }
}
