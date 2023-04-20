package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int result = 1;
        for(int i=2; i <= N; i++) {
            result *= i;
        }
        System.out.println(result);
    }
}
