package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14940_쉬운최단거리 {
	
	static class Point {
		int r, c, dst;
		public Point(int r, int c, int dst) {
			this.r = r;
			this.c = c;
			this.dst = dst;
		}
	}
	static int N, M, map[][], GO=-1;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		Point p = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = GO;
				if(map[i][j] == 2) p = new Point(i, j, 0);
			}
		}
		bfs(p);
		
		StringBuilder sb = new StringBuilder();		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]+" ");				
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void bfs(Point p) {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(p);
		map[p.r][p.c] = 0;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != GO) continue;
				queue.add(new Point(nr, nc, cur.dst+1));
				map[nr][nc] = cur.dst+1;
			}
		}
	}
}
