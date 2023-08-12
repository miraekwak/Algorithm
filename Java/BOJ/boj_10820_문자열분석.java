package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10820_문자열분석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input=br.readLine())!=null) {
            int up_cnt = 0;
            int down_cnt = 0;
            int num_cnt = 0;
            int space_cnt = 0;
            char[] line = input.toCharArray();
            for(char c: line) {
                if('A' <= c && c<='Z'){
                    up_cnt++;
                }
                else if('a' <= c && c <= 'z') {
                    down_cnt++;
                }
                else if(c == ' ') {
                    space_cnt++;
                }
                else {
                    num_cnt++;
                }

            }
            System.out.println(down_cnt+" "+up_cnt+" "+num_cnt+" "+space_cnt);
        }
    }
}
