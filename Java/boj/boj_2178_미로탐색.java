package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 */
public class boj_2178_미로탐색 {
	private static int N, M, map[][];
	private static int dr[] = {1, -1, 0, 0};
	private static int dc[] = {0, 0, 1, -1};
	
	static class Point {
		int r, c, d;
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.valueOf(line.charAt(j)-'0');				
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(new Point(0, 0, 1));
		map[0][0] += 1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(cur.r == N-1 && cur.c == M-1) return cur.d;
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(isOutOfRange(nr, nc) || map[nr][nc] != 1) continue;
				map[nr][nc] += 1;
				queue.add(new Point(nr, nc, cur.d + 1));
			}
		}
		return -1;
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
