package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * NxN
 * 1x2(-), 2x1(|), 2x2(\) 방향 파이프
 * 파이프 밀기
 * 	- 오른쪽 아래
 * 	- 아래
 * 	- 오른쪽 아래 대각선
 * 회전 45도
 * 	- 가로 => 2가지
 *  - 세로 => 2가지
 *  - 대각선 => 3가지
 * 
 * 파이프 처음 위치 (1,1)-(1,2)
 * 빈칸 0, 벽 1
 * 
 * 알고리즘] DFS
 * 1,1 에서 시작 N,N 도착
 * 각 위치에서 3가지 방향이동
 * - 우
 * - 하
 * - 우하
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_17070_파이프옮기기1_DFS {

	static int N, CNT;
	static boolean map[][];
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N+2][N+2];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		for(int i=0; i<N+2; i++) {
			map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = true; 
		}
		dfs(1, 2, 0);
		System.out.println(CNT);
	}
	
	// d : 0=가로, 1=세로, 2=대각선
	private static void dfs(int r, int c, int d) {
		// 벽이면 종료
		if(map[r][c]) return;
		
		if(r == N && c == N) {
			CNT++;
			return;
		}
		
		if(d!=1) dfs(r, c+1, 0); // 가로, 대각선일 경우 가로로 이동 가능
		if(d!=0) dfs(r+1, c, 1); // 세로, 대각선일 경우 세로로 이동 가능
		if(!map[r+1][c] && !map[r][c+1]) dfs(r+1, c+1, 2); // 범위내 벽이 없다면 모든 경우 대각선 이동 가능
	}
}
