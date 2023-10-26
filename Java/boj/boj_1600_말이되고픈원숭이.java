package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고리즘]
 * bfs 사용
 * 3차원배열 행 x 열 x K+1
 * 시작점에서
 * - 말로 이동
 * - 상하좌우 이동
 * 도착지점 도착 시 최단 경로 반환
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_1600_말이되고픈원숭이 {

	static int K, W, H;
	static int monkeyD[][] = {
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};
	static int horseD[][] = {
			{-1, -2, -2, -1, 1, 2, 2, 1},
			{-2, -1, 1, 2, 2, 1, -1, -2}
	};
	static boolean visited[][][];
	static class Monkey {
		int r, c, k, dst;
		public Monkey(int r, int c, int k, int dst) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.dst = dst;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		visited = new boolean[H][W][K+1];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)  {
					for(int k=0; k<K+1; k++) visited[i][j][k] = true;
				}
			}
		}
		
		System.out.println(findRoad());
	}
	private static int findRoad() {		
		Queue<Monkey> queue = new ArrayDeque<Monkey>();
		queue.add(new Monkey(0, 0, 0, 0));
		for(int k=0; k<K+1; k++) visited[0][0][k] = true;

		while(!queue.isEmpty()) {
			Monkey curr = queue.poll();

			if(curr.r == H-1 && curr.c == W-1) return curr.dst;
			
			if(curr.k < K) {
				int nk = curr.k + 1;
				for(int d=0; d<8; d++) {
					int nr = curr.r + horseD[0][d];
					int nc = curr.c + horseD[1][d];
					if(isOutOfRange(nr, nc) || visited[nr][nc][nk]) continue;
					visited[nr][nc][nk] = true;
					queue.add(new Monkey(nr, nc, nk, curr.dst+1));
				}
			}
			
			for(int d=0; d<4; d++) {
				int nr = curr.r + monkeyD[0][d];
				int nc = curr.c + monkeyD[1][d];
				if(isOutOfRange(nr, nc) || visited[nr][nc][curr.k]) continue;
				visited[nr][nc][curr.k] = true;
				queue.add(new Monkey(nr, nc, curr.k, curr.dst+1));
			}
		}
		
		return -1;
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= H || c < 0 || c >= W;
	}
}

