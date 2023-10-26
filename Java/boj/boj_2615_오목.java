package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_2615_오목 {
    private static final int MAX_NUM = 19;
    private static int[][] map;
    private static int[] dr = {-1, 0, 1, 1}; //우상, 우, 우하, 하
    private static int[] dc = {1, 1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[MAX_NUM+2][MAX_NUM+2];

        for(int i=1; i<=MAX_NUM; i++) {
            String[] line = br.readLine().split(" ");
            for(int j=1; j<=MAX_NUM; j++) {
                map[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int i=1; i<=MAX_NUM; i++) {
            for(int j=1; j<=MAX_NUM; j++) {
                if(map[i][j] == 0) continue;

                for(int d=0; d<4; d++) {
                    int prevR = i - dr[d];
                    int prevL = j - dc[d];
                    if(map[prevR][prevL] == map[i][j]) continue;

                    int cnt = 1;
                    int r = i;
                    int c = j;
                    while(true) {
                        r+=dr[d];
                        c+=dc[d];
                        if(map[i][j] != map[r][c]) break;
                        cnt++;
                    }
                    if(cnt == 5) {
                        System.out.println(map[i][j]);
                        System.out.println(i + " "+ j);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}
