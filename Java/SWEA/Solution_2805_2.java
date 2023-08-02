package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_2 {

	private static int[][] map;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			// 입력
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split("");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			// 농작물 수확
			int total = getRevenue(N);
			sb.append("#"+t+" "+total+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getRevenue(int size) {
		int total = 0; // 농작물 수익
		int row = 0; // 농장 행
		int pass = size/2; // 0행에서 수확 시작위치
		
		// 위쪽 절반 농작물 수확
		for(int i=1; i<=size; i+=2) {
			for(int j=pass; j<pass+i; j++) {
				total += map[row][j];
			}
			pass--;
			row++;
		}
		
		// 아래쪽 절반 농작물 수확
		pass = 1; // 중앙 -1행의 수확 시작 위치
		for(int i=size-2; i>=1; i-=2) {
			for(int j=pass; j<pass+i; j++) {
				total += map[row][j];
			}
			pass++;
			row++;
		}
		return total;
	}
}
