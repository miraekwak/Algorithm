package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * 알고리즘]
 * 2차원 배열에 i, j위치까지 도둑 루피 합 저장
 * 시작위치에서 끝위치에 도달할 때까지 bfs
 * - 큐에서 poll
 * - 사방 탐색
 * - 도둑 루피 합이 최소라면 갱신
 * - 큐 삽입
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_4485_녹색옷입은애가젤다지 {

	static int N, map[][] = new int[125][125];
	static int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};
	static boolean visited[][];
	
	static class Point implements Comparable<Point>{
		int r, c, rupee;
		public Point(int r, int c, int rupee) {
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}
		@Override
		public int compareTo(Point o) {
			return this.rupee - o.rupee;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int problem = 1;
		StringTokenizer st = null;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("Problem "+(problem++)+": "+ getRupee()+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getRupee() {
		visited = new boolean[N][N];
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		queue.add(new Point(0, 0, map[0][0]));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			if(curr.r == N-1 && curr.c == N-1) return curr.rupee;
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				queue.add(new Point(nr, nc, curr.rupee + map[nr][nc]));
			}
		}
		return -1;
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
}
