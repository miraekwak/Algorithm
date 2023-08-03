package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1247 {

	private static int[] dr; // x 좌표
	private static int[] dc; // y 좌표
	private static boolean[] visited;
	private static int N, path, dst_r, dst_c;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			dr = new int[N];
			dc = new int[N];
			visited = new boolean[N];
			path = Integer.MAX_VALUE;
			
			String[] line = br.readLine().split(" ");
			int str_r = Integer.parseInt(line[0]); // 출발지 좌표
			int str_c = Integer.parseInt(line[1]);
			dst_r = Integer.parseInt(line[2]); // 목적지 좌표
			dst_c = Integer.parseInt(line[3]);
			for(int i=4, j=0; i<line.length; i+=2, j++) {
				dr[j] = Integer.parseInt(line[i]);
				dc[j] = Integer.parseInt(line[i+1]);
			}

			// 최적 경로 탐색
			search(str_r, str_c, 0, 0);
			sb.append("#"+t+" "+path+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 재귀를 이용한 최적 경로 탐색
	 * @param r : 현재 x좌표
	 * @param c : 현재 y좌표
	 * @param num : 현재까지 거리 합
	 * @param idx : 현재까지 선택한 요소 개수
	 */
	private static void search(int r, int c, int num, int idx) {
		if(num >= path) return; // 거리합이 최소 거리 합보다 클 경우 탐색 종료
		if(idx == N) {
			path = Math.min(path, num+distance(r, c, dst_r, dst_c));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			search(dr[i], dc[i], num+distance(r, c, dr[i], dc[i]), idx+1);
			visited[i] = false;
		}
	}
	
	/**
	 * 거리 계산 함수
	 * @param r1 : 출발지 x좌표
	 * @param c1 : 출발지 y좌표
	 * @param r2 : 도착지 x좌표
	 * @param c2 : 도착지 y좌표
	 * @return
	 */
	private static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) +  Math.abs(c1 - c2);
	}

}
