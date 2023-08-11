package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_11052_카드구매하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] cards = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(i -> Integer.parseInt(i))
                .toArray();
        int[] price = new int[N+1];
        for(int i = 1; i<=N; i++) {
            for(int j = i; j<=N; j++) {
                price[j] = Math.max(price[j], price[j-i]+cards[i-1]);
            }
        }
        System.out.println(price[N]);
    }
}
