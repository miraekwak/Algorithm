package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;

public class Solution_1859 {
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            int[] a = new int[n];
            for(int i=0; i<n; i++) {
                a[i] = Integer.parseInt(input[i]);
            }
            int max = a[n-1];
            long benefit = 0;
            for(int i=n-2; i>=0; i--) {
                if(max > a[i]) {
                    benefit += max - a[i];
                }
                else {
                    max = a[i];
                }
            }
            sb.append("#"+test_case+" "+benefit+"\n");
        }
        System.out.println(sb);
    }
}
