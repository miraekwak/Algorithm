package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1210 {
    private static final int MAX_SIZE = 100; // 사다리 크기
    private static int T; // 테스트케이스 번호
    private static int[][] map; // 사다리 맵
    private static int dstC; // 목적지 열 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[MAX_SIZE+2][MAX_SIZE+2];
        for(int t=0; t<10; t++) {
            // 입력
            T = Integer.parseInt(br.readLine());
            for(int i=1; i<=MAX_SIZE; i++) {
                String[] line = br.readLine().split(" ");
                for(int j=1; j<=MAX_SIZE; j++) {
                    map[i][j] = Integer.parseInt(line[j-1]);
                }
            }
            // 목적지 찾기
            for(int i=1; i<=MAX_SIZE; i++) {
                if(map[MAX_SIZE][i] == 2) {
                    dstC = i;
                    break;
                }
            }
            // 출발지 검색 및 출력
            System.out.println("#"+T+" "+search());
        }
    }

    /**
     * 목적지에서 부터 도착가능한 출발지 찾는 함수
     * @return 출발지 열 좌표
     */
    private static int search() {
        int r = MAX_SIZE;
        int c = dstC;

        while(r > 1) {
            if(map[r][c-1] == 1) { // 좌로 이동 가능시 끝까지 이동
                while(map[r][c-1] == 1) {
                    c--;
                }
            }
            else if(map[r][c+1] == 1) { // 우로 이동 가능시 끝까지 이동
                while(map[r][c+1] == 1) {
                    c++;
                }
            }
            r--; // 좌,우 이동 후 또는 이동 못했을 경우 위로 이동
        }
        return c-1;
    }
}
