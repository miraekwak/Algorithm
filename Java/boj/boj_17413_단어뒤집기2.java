package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<S.length; i++) {
            if(S[i] == '<') {
                while(S[i] != '>') {
                    sb.append(S[i]);
                    i++;
                }
                sb.append('>');
            }
            else if(S[i] == ' ') {
                sb.append(' ');
            }
            else {
                StringBuilder nsb = new StringBuilder();
                while(S[i] != ' ' & S[i] != '<') {
                    nsb.append(S[i]);
                    i++;
                    if(i >= S.length) break;
                }
                sb.append(nsb.reverse());
                i--;
            }
        }
        System.out.println(sb);
    }
}
