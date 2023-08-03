package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_4012 {

	private static int[][] senerge;
	private static boolean[] visited;
	private static int N, tasteDiff;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			
			senerge = new int[N][N];
			visited = new boolean[N];
			tasteDiff = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					senerge[i][j] = Integer.parseInt(line[j]);					
				}
			}
			
			// 최소 맛 차이 찾기
			choose(0, 0, N/2);
			
			sb.append("#"+t+" "+tasteDiff+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 조합을 이용해 두 음식으로 나누고 최소 맛차이 구하는 함수
	 * @param cnt : 현재까지 선택한 재료 개수
	 * @param str : 선택할 수 있는 idx 시작 위치
	 * @param r : 총 선택할 수 있는 재료 개수
	 */
	private static void choose(int cnt, int str, int r) {
		if(cnt == r) {
			tasteDiff = Math.min(tasteDiff, getDiff());				
			return;
		}
		for(int i=str; i<N; i++) {
			visited[i] = true;
			choose(cnt+1, i+1, r);
			visited[i] = false;			
		}
	}
	
	/**
	 * 선택 여부를 통한 두 음식간 맛 차이 구하는 함수
	 * @return 두 음식 간 맛 차이
	 */
	private static int getDiff() {
		int diff = 0;
		for(int i=0; i<N; i++) {
			// true 또는 false에 따라 음식 A, B로 나뉨
			boolean food = visited[i];
			// 시너지를 낼 수 있는 같은 음식 재료 탐색
			for(int j=i+1; j<N; j++) {
				if(food == visited[j]) {
					// 같은 팀일 경우
					diff += food ? senerge[i][j]+senerge[j][i] : -(senerge[i][j]+senerge[j][i]); 
				}
			}
		}
		return Math.abs(diff);
	}
}
