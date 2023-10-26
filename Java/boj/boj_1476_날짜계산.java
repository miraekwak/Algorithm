package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1476_날짜계산 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bufferedReader.readLine().split(" ");
        int E = Integer.parseInt(line[0]);
        int S = Integer.parseInt(line[1]);
        int M = Integer.parseInt(line[2]);
        int year = 1;
        int e = 1;
        int s = 1;
        int m = 1;
        while(e != E || s != S || m != M) {
            if(e >= 15) {
                e = 1;
            } else {
                e++;
            }
            if(s >= 28) {
                s = 1;
            } else {
                s++;
            }
            if(m >= 19) {
                m = 1;
            } else {
                m++;
            }
            year++;
        }
        System.out.println(year);
    }
}
