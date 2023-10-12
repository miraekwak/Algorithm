package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7569_토마토 {
	
	static class Point{
		int r, c, h;
		public Point(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}
	static int N, M, H, tomato[][][];
	static int dr[] = {1, -1, 0, 0, 0, 0};
	static int dc[] = {0, 0, 1, -1, 0, 0};
	static int dh[] = {0, 0, 0, 0, 1, -1};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomato = new int[N][M][H];
		Queue<Point> queue = new ArrayDeque<Point>();
		int notTomatoCnt = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tomato[i][j][h] = Integer.parseInt(st.nextToken());
					if(tomato[i][j][h] == 1) queue.add(new Point(i, j, h));
					if(tomato[i][j][h] == 0) notTomatoCnt++;
				}
			}			
		}
		System.out.println(getDays(queue, notTomatoCnt));
	}

	private static int getDays(Queue<Point> queue, int cnt) {
		int day = 0;
		while(cnt > 0) {
			if(queue.isEmpty()) return -1;
			int size = queue.size();
			while(size-- > 0) {
				Point cur = queue.poll();
				for(int d=0; d<6; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					int nh = cur.h + dh[d];
					if(nr < 0 || nr >= N | nc < 0 || nc >= M || nh < 0 || nh >= H || tomato[nr][nc][nh] != 0) continue;
					queue.add(new Point(nr, nc, nh));
					tomato[nr][nc][nh] = 1;
					cnt--;
				}
			}
			day++;
		}
		return day;
	}
}
