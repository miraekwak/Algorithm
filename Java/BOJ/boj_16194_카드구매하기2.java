package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_16194_카드구매하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] cards = Arrays.stream(bufferedReader.readLine().split(" "))
                .mapToInt(i -> Integer.parseInt(i))
                .toArray();
        int[] price = new int[N+1];
        for(int i=1; i<=N; i++) {
            price[i] = cards[0] + price[i-1];
        }
        for(int i=2; i<=N; i++) {
            for(int j=i; j<=N; j++) {
                price[j] = Math.min(price[j], price[j-i] + cards[i-1]);
            }
        }
        System.out.println(price[N]);
    }
}
