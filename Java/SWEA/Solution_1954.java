package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_1954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++) {
            int n = Integer.valueOf(br.readLine());
            int[][] snail = snail(n);
            sb.append("#"+t+"\n");
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    sb.append(snail[i][j]+" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    public static int[][] snail(int n) {
        int[][] snail = new int[n][n];
        int cnt = 0;
        int r=0, c = 0;
        int[] pr = {0, 1, 0, -1};
        int[] pc = {1, 0, -1, 0};
        int idx = 0;
        while(cnt++ < n*n) {
            snail[r][c] = cnt;
            if(idx == 3) {
                if(r+pr[idx] < 0 || snail[r+pr[idx]][c+pc[idx]] != 0) {
                    idx = 0;
                }
            }
            else {
                if(c+pc[idx] >= n || r+pr[idx] >= n || c+pc[idx] < 0 || snail[r+pr[idx]][c+pc[idx]]!=0) {
                    idx++;
                }
            }
            r += pr[idx];
            c += pc[idx];
        }
        return snail;
    }
}
