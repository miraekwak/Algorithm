package BOJ;

import java.io.IOException;
import java.util.Scanner;

public class boj_1107 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();;
        boolean[] broken = new boolean[10];
        for(int i=0; i<M; i++) {
            broken[scan.nextInt()] = true;
        }
        int count = Math.abs(N-100);
        for(int i=0; i<= 999999; i++) {
            String c = Integer.toString(i);
            boolean isBreak = false;
            for(int j=0; j<c.length(); j++) {
                if(broken[c.charAt(j)-'0']) {
                    isBreak = true;
                    break;
                }
            }
            if(!isBreak) {
                count = Math.min(count, Math.abs(N-i) + c.length());
            }
        }
        System.out.println(count);
    }
}
