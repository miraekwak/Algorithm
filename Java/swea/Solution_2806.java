package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2806 {
    private static int cnt;
    private static int[] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=T; i++) {
            cnt = 0;
            int N = Integer.valueOf(br.readLine());
            board = new int[N];
            dfs(0);
            sb.append("#"+i+" "+cnt+"\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int row) {
        if(row == board.length) {
            cnt++;
            return;
        }
        boolean isPossible;
        for(int i=0; i<board.length; i++) {
            isPossible = true;
            for(int j=0; j<row; j++) {
                if(board[j] == i || i == board[j] + (row - j) || i == board[j] - (row - j)) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) {
                board[row] = i;
                dfs(row+1);
            }
        }
    }
}
