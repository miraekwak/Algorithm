package swea;

import java.util.Scanner;

public class Solution_1959 {
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for(int t = 1; t<=testcase; t++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] ai = new int[N];
            int[] bi = new int[M];

            for(int i=0; i<N; i++) {
                ai[i] = sc.nextInt();
            }
            for(int i=0; i<M; i++) {
                bi[i] = sc.nextInt();
            }

            int max = Integer.MIN_VALUE;
            int STD = Math.min(N, M);

            for(int i=0; i<Math.abs(N-M)+1; i++) {
                int sum = 0;
                for(int j=0; j<STD; j++) {
                    if(N>M) {
                        sum = sum + ai[i+j] * bi[j];
                    }
                    else {
                        sum = sum + ai[j] * bi[i+j];
                    }
                }
                max = Math.max(max, sum);
            }
            System.out.println("#" + t + " " + max);
        }
    }
}
