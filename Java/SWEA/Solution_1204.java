package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int t = Integer.valueOf(br.readLine());
            String[] score = br.readLine().split(" ");
            int[] cnt = new int[101];
            for(int i=0; i<1000; i++) {
                cnt[Integer.valueOf(score[i])] += 1;
            }
            int max = 0;
            for(int i=1; i<100; i++) {
                if(cnt[max] <= cnt[i]) {
                    max = i;
                }
            }
            sb.append("#"+t+" "+max+"\n");
        }
        System.out.println(sb);
    }
}
