package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int max_num = 1000000;
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] dp = new int[N+1];
        for(int i=2; i<=N; i++) {
            int cnt_2 = max_num;
            int cnt_3 = max_num;
            if(i%2 == 0) {
                cnt_2 = dp[i/2] + 1;
            }
            if(i%3 == 0) {
                cnt_3 = dp[i/3] + 1;
            }
            int min_num = Math.min(cnt_2, cnt_3);
            dp[i] = Math.min(dp[i-1]+1, min_num);
        }
        System.out.println(dp[N]);
    }
}
