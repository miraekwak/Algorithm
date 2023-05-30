package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1992 {
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            String[] line = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        String result = isPossiblePress(0, 0, N);
        System.out.println(result);
    }

    private static String divAndQnq(int rowStr, int colStr, int level) {
        String value = "(";
        value += isPossiblePress(rowStr, colStr, level/2);
        value += isPossiblePress(rowStr, colStr+level/2, level/2);
        value += isPossiblePress(rowStr+level/2, colStr, level/2);
        value += isPossiblePress(rowStr+level/2, colStr+level/2, level/2);
        value += ")";
        return value;
    }

    private static String isPossiblePress(int rowStr, int colStr, int level) {
        boolean isPossible = true;
        int standard = map[rowStr][colStr];
        for(int i=rowStr; i<rowStr+level; i++) {
            for(int j=colStr; j<colStr+level; j++) {
                if(map[i][j] != standard) {
                    isPossible = false;
                    break;
                }
            }
        }
        if(isPossible) {
            return Integer.toString(standard);
        }
        else {
            return divAndQnq(rowStr, colStr, level);
        }
    }
}
