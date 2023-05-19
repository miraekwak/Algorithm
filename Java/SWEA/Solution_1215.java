package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1215 {
    public static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=10; t++) {
            int len = Integer.parseInt(br.readLine());
            board = new char[8][8];
            for(int i=0; i<8; i++) {
                board[i] = br.readLine().toCharArray();
            }
            sb.append("#"+t+" "+(checkRow(len)+checkCol(len))+"\n");
        }
        System.out.println(sb);
    }
    public static int checkRow(int len) {
        int cnt = 0;
        for(int r=0; r<board.length; r++) {
            for(int c=0; c<= board.length-len; c++) {
                if(isRowPalindrome(r, c, len)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static int checkCol(int len) {
        int cnt = 0;
        for(int c=0; c<board.length; c++) {
            for(int r=0; r<= board.length-len; r++) {
                if(isColPalindrome(r, c, len)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static boolean isRowPalindrome(int row, int col, int len) {
        for(int i=0; i<len/2; i++) {
            if(board[row][col+i] != board[row][col+len-1-i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isColPalindrome(int row, int col, int len) {
        for(int i=0; i<len/2; i++) {
            if(board[row+i][col] != board[row+len-1-i][col]) {
                return false;
            }
        }
        return true;
    }
}
