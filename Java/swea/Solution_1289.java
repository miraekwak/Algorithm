package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=T; i++) {
            String s = br.readLine();
            int cnt = getCount(s, "0000", 0);
            sb.append("#"+i+" "+cnt+"\n");
        }
        System.out.println(sb);
    }

    public static int getCount(String original, String curr, int idx) {
        if(idx == original.length()) {
            return 0;
        }
        if(original.charAt(idx) == curr.charAt(idx)) {
            return getCount(original, curr, idx+1);
        }
        else {
            String newBit = "";
            for(int i=0; i<idx; i++) {
                newBit += curr.charAt(i);
            }
            for(int i=idx; i<original.length(); i++) {
                newBit += original.charAt(idx);
            }
            return 1 + getCount(original, newBit, idx+1);
        }
    }
}
