package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_3085_사탕게임 {
    public static char[][] board;
    public static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        board = new char[N][N];
        for(int i=0; i<N; i++) {
            board[i] = bufferedReader.readLine().toCharArray();
        }
        // row
        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                swapCandy(i, j, i, j+1);
                countCandy();
                swapCandy(i, j, i, j+1);
            }
        }
        // col
        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                swapCandy(j, i, j+1, i);
                countCandy();
                swapCandy(j, i, j+1, i);
            }
        }
        System.out.println(max);
    }
    public static void swapCandy(int r_1, int c_1, int r_2, int c_2) {
        char temp = board[r_1][c_1];
        board[r_1][c_1] = board[r_2][c_2];
        board[r_2][c_2] = temp;
    }

    public static int countCandy() {
        // row
        for(int i=0; i<board.length; i++) {
            int cnt = 1;
            for(int j=0; j<board.length-1; j++) {
                if(board[i][j] == board[i][j+1]) {
                    cnt++;
                }else {
                    cnt = 1;
                }
                max = Math.max(max, cnt);
            }
        }
        // col
        for(int i=0; i<board.length; i++) {
            int cnt = 1;
            for(int j=0; j<board.length-1; j++) {
                if(board[j][i] == board[j+1][i]) {
                    cnt++;
                }
                else {
                    cnt = 1;
                }
                max = Math.max(max, cnt);
            }
        }
        return max;
    }
}
