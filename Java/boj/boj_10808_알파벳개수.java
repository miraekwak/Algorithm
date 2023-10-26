package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10808_알파벳개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        int[] count = new int[26];
        for(char c: line) {
            int idx = c - 'a';
            count[idx] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int c: count) {
            sb.append(c).append(' ');
        }
        System.out.println(sb);
    }
}
