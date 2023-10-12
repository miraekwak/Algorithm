package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576_토마토 {
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, tomato[][];
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];
		Queue<Point> queue = new ArrayDeque<Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if(tomato[i][j] == 1) queue.add(new Point(i, j));
			}
		}
		System.out.println(getDays(queue));
	}

	private static int getDays(Queue<Point> queue) {
		int day = 0;
		while(!isAllRed()) {
			if(queue.isEmpty()) return -1;
			int size = queue.size();
			while(size-- > 0) {
				Point cur = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if(nr < 0 || nr >= N | nc < 0 || nc >= M || tomato[nr][nc] != 0) continue;
					queue.add(new Point(nr, nc));
					tomato[nr][nc] = 1;
				}
			}
			day++;
		}
		return day;
	}

	private static boolean isAllRed() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tomato[i][j] == 0) return false;
			}
		}
		return true;
	}	
}
