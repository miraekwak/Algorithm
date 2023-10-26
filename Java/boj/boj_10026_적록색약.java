package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

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
public class boj_10026_적록색약 {
	
	private static int N, RGCnt, Cnt;
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
		
		StringTokenizer st;		
		for(int i=0; i<N; i++) {
			grid[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					BFS(i, j, grid[i][j], false);
					Cnt++;
				}
				if(!rgVisited[i][j]) {
					BFS(i, j, grid[i][j], true);
					RGCnt++;
				}
			}
		}
		
		System.out.println(Cnt + " "+RGCnt);
	}

	private static void BFS(int r, int c, char color, boolean isRG) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		if(color=='B') {
			visited[r][c] = rgVisited[r][c] = true;
			RGCnt++;
		}
		else {
			if(isRG) rgVisited[r][c] = true;
			else visited[r][c] = true;
			
		}
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = curr[0]+dr[i], nc = curr[1]+dc[i];
				if(!isInRange(nr, nc)) continue;
				if(color=='B') {
					if(grid[nr][nc] == 'B' && !visited[nr][nc]) {
						visited[nr][nc] = true;
						rgVisited[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
					continue;
				}
				if(isRG) {
					if(!rgVisited[nr][nc] && (grid[nr][nc] == 'R' || grid[nr][nc] == 'G')) {
						rgVisited[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
					continue;
				}
				if(!visited[nr][nc] && grid[nr][nc] == color) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	} 
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
