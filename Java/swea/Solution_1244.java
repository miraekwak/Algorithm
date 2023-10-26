package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_1244 {
    private static StringBuilder numbers;
    private static int changeCnt;
    private static int maxNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++) {
            String[] line = br.readLine().split(" ");
            changeCnt = Integer.valueOf(line[1]);
            numbers = new StringBuilder(line[0].toString());
            if(changeCnt > numbers.length()) {
                changeCnt = numbers.length();
            }
            maxNum = 0;
            dfs(0, 0);
            sb.append("#"+t+" "+maxNum+"\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int str, int change) {
        if(change >= changeCnt) {
            maxNum = Math.max(maxNum, Integer.valueOf(numbers.toString()));
            return;
        }
        for(int i=str; i<numbers.length(); i++) {
            for(int j=i+1; j<numbers.length(); j++) {
                swap(i, j);
                dfs(str,change+1);
                swap(i, j);
            }
        }
    }

    public static void swap(int a, int b) {
        char temp = numbers.charAt(a);
        numbers.setCharAt(a, numbers.charAt(b));
        numbers.setCharAt(b, temp);
    }
}
