package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954_2 {

	private static int[] dr = {0, 0, 1, 0, -1};
	private static int[] dc = {0, 1, 0, -1, 0};
	private static int[][] map;
	private static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			
			// 달팽이숫자
//			makeSnail(); // 반복문 함수
			makeSnail(0,0,1,1); // 재귀함수
			
			// 출력
			sb.append("#"+t+"\n");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j]+" ");
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}
	
	/**
	 * 반복문 사용해서 풀기
	 * @param n : 맵 크기
	 */
	private static void makeSnail() {
		int r = 0, c = 0;
		int d = 1;
		int curr = 1;

		while(true) {
			map[r][c] = curr;
			if(curr==N*N) break;
			
			int next_r = r + dr[d];
			int next_c = c + dc[d];
			
			// 범위를 벗어나거나 이미 숫자가 채워진 경우 방향 바꾸기
			if(next_r<0 || next_r>=N || next_c<0 || next_c>=N || map[next_r][next_c] != 0) {
				d = (d+1) % 5;
				continue;
			}
			r = next_r;
			c = next_c;
			curr++;
		}
	}
	
	/**
	 * 재귀를 사용해서 풀기
	 * @param r : 현재 행 위치
	 * @param c : 현재 열 위치
	 * @param d : 현재 방향 (좌->하->상->우->..)중 하나
	 * @param num : 현재 숫자
	 */
	private static void makeSnail(int r, int c, int d, int num) {
		map[r][c] = num;
		if(num == N*N) {
			return;
		}
		
		int next_r = r + dr[d];
		int next_c = c + dc[d];
		
		// 범위를 벗어나거나 이미 숫자가 채워진 경우 방향 바꾸기
		if(next_r<0 || next_r>=N || next_c<0 || next_c>=N || map[next_r][next_c] != 0) {
			makeSnail(r, c, (d+1) % 5, num);
		}
		else {
			makeSnail(next_r, next_c, d, num+1);
		}
		
	}
}
