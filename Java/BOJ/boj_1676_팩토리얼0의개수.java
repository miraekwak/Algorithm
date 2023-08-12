package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1676_팩토리얼0의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(bufferedReader.readLine());
        int cnt_2 = 0;
        int cnt_5 = 0;
        for(int i=1; i<=N; i++) {
            int target = i;
            while(target % 2 == 0) {
                cnt_2++;
                target = target/2;
            }
            while (target % 5 == 0) {
                cnt_5++;
                target = target/5;
            }
        }
        System.out.println(Math.min(cnt_2, cnt_5));
    }
}
