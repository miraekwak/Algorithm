package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=10; t++) {
            int dump = Integer.valueOf(br.readLine());
            int[] height = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(i-> Integer.valueOf(i))
                    .toArray();
            for(int i=0; i<dump; i++) {
                int maxIdx=0;
                int minIdx=0;
                for(int j=1; j<height.length; j++) {
                    if(height[maxIdx] <= height[j]) {
                        maxIdx = j;
                    }
                    if(height[minIdx] >= height[j]) {
                        minIdx = j;
                    }
                }
                height[maxIdx] -= 1;
                height[minIdx] += 1;
            }
            int maxIdx=0;
            int minIdx=0;
            for(int j=1; j<height.length; j++) {
                if(height[maxIdx] <= height[j]) {
                    maxIdx = j;
                }
                if(height[minIdx] >= height[j]) {
                    minIdx = j;
                }
            }
            sb.append("#"+t+" "+(height[maxIdx] - height[minIdx])+"\n");
        }
        System.out.println(sb);
    }
}
