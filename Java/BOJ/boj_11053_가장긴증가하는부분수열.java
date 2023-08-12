package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class boj_11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(i-> Integer.valueOf(i)).toArray();
        int[] dp = new int[N];
        for(int i=0; i<N; i++){
            dp[i] = 1;
            for(int j=i-1; j>=0; j--) {
                if(numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
