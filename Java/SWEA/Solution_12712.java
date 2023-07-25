package SWEA;

import java.util.Scanner;

public class Solution_12712 {
    private static int[][] map;
    private static int N;
    private static int M;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for(int t=1; t<=test; t++) {

            N = sc.nextInt();
            map = new int[N][N];
            M = sc.nextInt();

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }


            int maximum_pari = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    maximum_pari = Math.max(maximum_pari, getMaxPari(i, j));
                }
            }
            System.out.println("#" + t + " " + maximum_pari);
        }
    }

    private static int getMaxPari(int r, int c) {
        // plus

        int plus = dfs(r-1, c, -1, 0, M-2)
                + dfs(r, c+1, 0, 1, M-2)
                + dfs(r+1, c, 1, 0, M-2)
                + dfs(r, c-1, 0, -1, M-2);

        int multiple = dfs(r-1, c-1, -1, -1, M-2)
                + dfs(r-1, c+1, -1, 1, M-2)
                + dfs(r+1, c+1, 1, 1, M-2)
                + dfs(r+1, c-1, 1, -1, M-2);

        return map[r][c] + Math.max(plus, multiple);
    }

    private static int dfs(int r, int c, int r_p, int c_p, int cnt) {
        if(r < 0 || r >= N) return 0;
        if(c < 0 || c >= N) return 0;

        if (cnt == 0) {
            return map[r][c];
        }

        return map[r][c] + dfs(r+r_p, c+c_p, r_p, c_p, cnt-1);
    }
}
