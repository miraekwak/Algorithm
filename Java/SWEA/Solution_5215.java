package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5215 {
    public static int maxTaste;
    public static int[] taste;
    public static int[] calorie;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++) {
            String[] order = br.readLine().split(" ");
            int N = Integer.parseInt(order[0]);
            int L = Integer.parseInt(order[1]);
            maxTaste = 0;
            taste = new int[N];
            calorie = new int[N];
            for(int i=0; i<N; i++) {
                String[] line = br.readLine().split(" ");
                taste[i] = Integer.parseInt(line[0]);
                calorie[i] = Integer.parseInt(line[1]);
            }
            maxTaste(0, N, 0, 0, L);
            sb.append("#"+t+" "+maxTaste+"\n");
        }
        System.out.println(sb);
    }
    public static void maxTaste(int str, int end, int currCal, int currTaste, int cal) {
        if(currCal > cal) {
            return;
        }
        else {
            maxTaste = Math.max(maxTaste, currTaste);
        }
        for(int i=str; i<end; i++) {
            maxTaste(i+1, end, currCal+calorie[i],currTaste+taste[i], cal);
        }
    }
}