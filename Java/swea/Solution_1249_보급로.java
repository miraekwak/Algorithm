package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


/**
 * 알고리즘]
 * bfs 탐색
 * 시작 위치에서 상하좌우 이동하며 끝위치 이동
 * 2차원 배열 1에 깊이 저장
 * 2차원 배열 2에 그 위치까지 가기 위한 복구시간
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_1249_보급로 {

	static int N, time[][], map[][];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static class Point {
		int r, c, time;
		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			time = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
				Arrays.fill(time[i], Integer.MAX_VALUE);
			}
			
			sb.append("#"+tc+" "+getMinTime()+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getMinTime() {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(new Point(0, 0, 0));
		time[0][0] = 0;
		
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			if(time[curr.r][curr.c] != curr.time) continue;
			if(curr.r == N-1 && curr.c == N-1) continue;
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if(isOutOfRange(nr, nc)) continue;
				int ntime = curr.time + map[nr][nc];
				if(time[nr][nc] <= ntime) continue;
				time[nr][nc] = ntime;
				queue.add(new Point(nr, nc, ntime));
			}
		}
		return time[N-1][N-1]==Integer.MAX_VALUE ? -1 : time[N-1][N-1];
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
}
