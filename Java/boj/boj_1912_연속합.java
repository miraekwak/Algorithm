package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(i -> Integer.parseInt(i))
                .toArray();
        int [] dp = numbers;
        for(int i=1; i<N; i++) {
            dp[i] = Math.max(numbers[i], dp[i-1] + numbers[i]);
        }
        int max = numbers[0];
        for(int i=1; i<N; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}
