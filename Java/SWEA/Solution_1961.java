package SWEA;

import java.util.Scanner;


public class Solution_1961 {
    private static int[][] map;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        for(int t = 1; t<=testcase; t++) {

            int N = sc.nextInt();

            map = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            String[] list = new String[N];

            // 90
            for(int c=0; c<N; c++) {
                String str = "";
                for(int r=N-1; r>=0; r--) {
                    str += map[r][c];
                }
                list[c] = str;
            }

            // 180
            int idx = 0;
            for(int r=N-1; r>=0; r--) {
                String str = "";
                for(int c=N-1; c>=0; c--) {
                    str += map[r][c];
                }
                list[idx] = list[idx] + " " + str;
                idx++;
            }

            // 180
            idx = 0;
            for(int c=N-1; c>=0; c--) {
                String str = "";
                for(int r=0; r<N; r++) {
                    str += map[r][c];
                }
                list[idx] = list[idx] + " " + str;
                idx++;
            }

            System.out.println("#" + t);
            for(int i=0; i<N; i++) {
                System.out.println(list[i]);
            }
        }
    }
}
