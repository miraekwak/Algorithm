package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj_5622 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] word = br.readLine().split("");
        int[] time = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int cnt = 0;
        for(int i=0; i<word.length; i++) {
            String alpha = word[i];
            if(alpha.equals("A") || alpha.equals("B")  || alpha.equals("C")) {
                cnt += time[2];
            }
            else if(alpha.equals("D") || alpha.equals("E")  || alpha.equals("F")) {
                cnt += time[3];

            }
            else if(alpha.equals("G") || alpha.equals("H")  || alpha.equals("I")) {
                cnt += time[4];
            }
            else if(alpha.equals("J") || alpha.equals("K")  || alpha.equals("L")) {
                cnt += time[5];
            }
            else if(alpha.equals("M") || alpha.equals("N")  || alpha.equals("O")) {
                cnt += time[6];
            }
            else if(alpha.equals("P") || alpha.equals("Q")  || alpha.equals("R") || alpha.equals("S")) {
                cnt += time[7];
            }
            else if(alpha.equals("T") || alpha.equals("U")  || alpha.equals("V")) {
                cnt += time[8];
            }
            else if(alpha.equals("W") || alpha.equals("X")  || alpha.equals("Y") || alpha.equals("Z")) {
                cnt += time[9];
            }
        }
        System.out.println(cnt);
    }
}
