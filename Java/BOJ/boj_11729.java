package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_11729 {
    private static int cnt = 0;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        move(1, 2, 3, N);
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void move(int from, int middle, int to, int N) {
        if(N == 0) return;
        move(from, to, middle, N-1);
        sb.append(from + " " + to + "\n");
        cnt++;
        move(middle, from, to, N-1);
    }
}
