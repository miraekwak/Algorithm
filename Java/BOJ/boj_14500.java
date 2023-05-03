package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_14500 {
    static int N, M, result = 0;;
    static int[][] board;
    static boolean[][] visited;
    static int[] row = {-1, 1, 0, 0};
    static int[] col = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] order = bufferedReader.readLine().split(" ");
        N = Integer.valueOf(order[0]);
        M = Integer.valueOf(order[1]);
        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            board[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(num -> Integer.valueOf(num)).toArray();
        }

        for(int r =0; r<N; r++) {
            for(int c=0; c<M; c++) {
                visited[r][c] = true;
                dfs(r, c, 1, board[r][c]);
                visited[r][c] = false;

                combi(0, 0, r, c, board[r][c]);
            }
        }
        System.out.println(result);
        bufferedReader.close();
    }

    static void dfs(int r, int c, int cnt, int sum) {
        if(cnt == 4) {
            result = Math.max(sum, result);
            return;
        }

        for(int i=0; i<4; i++) {
            int nr = r + row[i];
            int nc = c + col[i];
            if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
            if(visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, cnt+1, sum+board[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    private static void combi(int cnt, int start, int r, int c, int sum) {
        if(cnt==3) {
            result = Math.max(result, sum);
            return;
        }

        for(int i=start; i<4; i++) {
            int nr = r + row[i];
            int nc = c + col[i];

            if(nr<0 || nr>=N || nc<0 || nc>=M) continue;

            combi(cnt+1, i+1, r, c, sum+board[nr][nc]);
        }
    }
}
