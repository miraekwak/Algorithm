package SWEA;

import java.util.Scanner;

public class Solution_1974 {
    private static int[][] map;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        map = new int[9][9];
        for(int t = 1; t<=testcase; t++) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            boolean isCheck = true;
            for(int i=0; i<9; i++) {
                if(!checkRow(i) || !checkCol(i)) {
                    isCheck = false;
                    break;
                }
            }

            for(int i=0; i<9; i=i+3) {
                for(int j=0; j<9; j=j+3) {
                    if(!check3by3(i, j)) {
                        isCheck = false;
                        break;
                    }
                }
            }

            System.out.println("#" + t + " " + (isCheck ? 1 : 0));
        }
    }

    private static boolean checkRow(int r) {
        boolean[] check = new boolean[10];
        for(int i=0; i<9; i++) {
            if(check[map[r][i]]) {
                return false;
            }
            check[map[r][i]] = true;
        }
        return true;
    }

    private static boolean checkCol(int c) {
        boolean[] check = new boolean[10];
        for(int i=0; i<9; i++) {
            if(check[map[i][c]]) {
                return false;
            }
            check[map[i][c]] = true;
        }
        return true;
    }

    private static boolean check3by3(int r, int c) {
        boolean[] check = new boolean[10];
        for(int i=r; i<r+3; i++) {
            for(int j=c; j<c+3; j++) {
                if(check[map[i][j]]) {
                    return false;
                }
                check[map[i][j]] = true;
            }
        }
        return true;
    }
}
