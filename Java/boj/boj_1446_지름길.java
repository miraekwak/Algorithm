import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제]
 * D킬로미터 거리 이동 시
 * 지름길 찾기
 *
 * 조건]
 *
 *
 */
public class boj_1446_지름길 {

    static class Shortcut {
        private int s;
        private int e;
        private int w;

        public Shortcut(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Shortcut>[] shortcuts = new ArrayList[10001];
        int[] dp = new int[10001];
        for (int i=0; i<10001; i++) {
            shortcuts[i] = new ArrayList<Shortcut>();
            dp[i] = i;
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(e-s < w || e > D) continue;
            shortcuts[e].add(new Shortcut(s, e, w));
        }

        for(int i=1; i <= D; i++) {
            dp[i] = Math.min(dp[i], dp[i-1]+1);
            if(!shortcuts[i].isEmpty()) {
                for(int k=0; k < shortcuts[i].size(); k++) {
                    Shortcut sc = shortcuts[i].get(k);
                    dp[i] = Math.min(dp[i], dp[sc.s] + sc.w);
                }
            }
        }
        System.out.println(dp[D]);
    }
}