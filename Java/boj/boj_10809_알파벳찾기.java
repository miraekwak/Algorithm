package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10809_알파벳찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        int[] alphabet = new int[26];
        for(int i=0; i<alphabet.length; i++) {
            alphabet[i] = -1;
        }
        for(int i=0; i<line.length; i++) {
            int idx = line[i] - 'a';
            if(alphabet[idx] == -1) {
                alphabet[idx] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int idx: alphabet) {
            sb.append(idx).append(' ');
        }
        System.out.println(sb);
    }
}
