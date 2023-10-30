package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 */
public class boj_2667_단지번호붙이기 {
	private static int N, map[][];
	private static int dr[] = {1, -1, 0, 0};
	private static int dc[] = {0, 0, 1, -1};
	private static List<Integer> counts = new ArrayList<Integer>();
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.valueOf(line.charAt(j)-'0');				
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) bfs(i, j);
			}
		}
		Collections.sort(counts);
		System.out.println(counts.size());
		for (int cnt : counts) {
			System.out.println(cnt);			
		}
	}

	private static void bfs(int rstr, int cstr) {
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(new Point(rstr, cstr));
		map[rstr][cstr] += 1;
		int cnt = 0;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			cnt++;
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(isOutOfRange(nr, nc) || map[nr][nc] != 1) continue;
				map[nr][nc] += 1;
				queue.add(new Point(nr, nc));
			}
		}
		counts.add(cnt);
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
}
