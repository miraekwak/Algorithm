package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 0;
        StringBuilder sb = new StringBuilder();
        while(T++ < 10) {
            int n = Integer.parseInt(br.readLine());
            int[] height = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(i->Integer.valueOf(i))
                    .toArray();
            int cnt = 0;
            for(int i=2; i<height.length-2; i++) {
                int left = height[i] - Math.max(height[i-1], height[i-2]);
                int right = height[i] - Math.max(height[i+1], height[i+2]);
                if(left > 0 && right >0) {
                    cnt += Math.min(left, right);
                }
            }
            sb.append("#"+T+" "+cnt+"\n");
        }
        System.out.println(sb);
    }
}
