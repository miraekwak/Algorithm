package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 문제]
 * RGB로 이루어진 NxN 행렬
 * R,G,B 그룹 수와 (RG),B 그룹 수 구하기
 * 
 * 풀이]
 * bfs를 사용해서 같은 구역 찾기
 * 방문 배열을 사용해 방문 하지 않은 곳에서 부터 BFS 시작
 * 
 * 적록색약 구역과 아닌 구역을 각각 찾아야 할까?
 * 파란색 구역은 동일
 * 빨강 초록에 대해서만 각각 탐색
 * 
 */
public class boj_10026_적록색약_2 {
	
	private static int N;
	private static char grid[][];
	private static boolean visited[][], rgVisited[][];
	private static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	private static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		grid = new char[N][];
		visited = new boolean[N][N];
		rgVisited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		int RGCnt=0, Cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					if(grid[i][j] == 'B') {
						BFS(i, j, 0);
						RGCnt++;// 적록색약인 경우 아닌경우 모두 동등하게 개수 증가
					}
					else BFS(i, j, 1);
					Cnt++;
				}
				if(!rgVisited[i][j]) {
					BFS(i, j, 2);
					RGCnt++;
				}
			}
		}
		
		System.out.println(Cnt + " "+RGCnt);
	}
	
	// flag :  0->B , 1->RorG, 2->RG
	private static void BFS(int r, int c, int flag) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		if(flag==0) visited[r][c] = rgVisited[r][c] = true;
		else if(flag == 1) visited[r][c] = true;
		else if(flag == 2) rgVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = curr[0]+dr[i], nc = curr[1]+dc[i];
				if(!isInRange(nr, nc)) continue;
				if(flag == 0 && grid[nr][nc] == 'B' && !visited[nr][nc]) {
					visited[nr][nc] = rgVisited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
				else if(flag == 1 && !visited[nr][nc] && grid[nr][nc] == grid[r][c]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
				else if(flag == 2 && !rgVisited[nr][nc] && grid[nr][nc] != 'B') {
					rgVisited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
