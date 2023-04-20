package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bufferedReader.readLine().split(" ");
        int N = Integer.valueOf(line[0]);
        int M = Integer.valueOf(line[1]);
        long cnt_2 = count2(N) - count2(M) - count2(N-M);
        long cnt_5 = count5(N) - count5(M) - count5(N-M);
        System.out.println(Math.min(cnt_2, cnt_5));
    }

    public static long count5(int num) {
        long temp = 0;
        if(num<5) return 0;
        for(long i=5; i<=num; i*=5) {
            temp += (num/i);
        }
        return temp;
    }

    public static long count2(int num) {
        long temp = 0;
        if(num<2) return 0;
        for(long i=2; i<=num; i*=2) {
            temp += (num/i);
        }
        return temp;
    }
}
