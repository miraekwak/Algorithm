package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=T; i++) {
            int N = Integer.valueOf(br.readLine());
            int s = 0;
            int mid = N/2 + 1;
            int str = mid-1;
            for(int j=1; j<mid*2; j+=2) {
                String line = br.readLine();
                for(int k=str; k<str+j; k++) {
                    s += Integer.parseInt(String.valueOf(line.charAt(k)));
                }
                str--;
            }
            str = 1;
            for(int j=(mid-1)*2-1; j>0; j-=2) {
                String line = br.readLine();
                for(int k=str; k<str+j; k++) {
                    s += Integer.parseInt(String.valueOf(line.charAt(k)));
                }
                str++;
            }
            sb.append("#"+i+" "+s+"\n");
        }
        System.out.println(sb);
    }
}
