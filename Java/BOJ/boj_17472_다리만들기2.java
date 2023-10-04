package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 알고리즘]
 * dfs를 사용해서 섬 구분
 * union-find를 해서 연결됐는지 확인?
 * 조합을 통해 각 섬에서 다른 섬까지 거리 계산 후 priority queue에 삽입
 * priority queue를 거리 순으로 정렬
 * 거리가 짧은 순으로 섬을 union
 * queue를 비웠을 때 parent가 같지 않다면 -1 반환
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_17472_다리만들기2 {

	static int N, M, CNT, map[][], NOT_VISITED = 7, parent[];
	static int D[][] = {
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};
	
	static class Bridge implements Comparable<Bridge>{
		int from, to, len;
		public Bridge(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}
		
		@Override
		public int compareTo(Bridge o) {
			return this.len - o.len;
		}
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot == broot) return false;
		parent[broot] = aroot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = NOT_VISITED;
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == NOT_VISITED) {
					CNT++;
					dfs(i, j);
				}
			}
		}
		
		// 가능한 다리 찾기
		PriorityQueue<Bridge> queue = new PriorityQueue<Bridge>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) continue;
				for(int d=0; d<4; d++) {
					int r=i+D[0][d], c=j+D[1][d];
					int len = 0;
					while(!isOutOfRange(r, c) && map[r][c] == 0) {
						len++;
						r += D[0][d];
						c += D[1][d];
					}
					if(!isOutOfRange(r, c) && map[i][j] != map[r][c] && len >= 2) {
						queue.add(new Bridge(map[i][j], map[r][c], len));
					}
				}
			}
		}
		
		// 길이가 작은 다리부터 선택하기
		parent = new int[CNT+1];
		for(int i=1; i<CNT+1; i++) parent[i] = i;
		int result = 0;
		int cnt = 0;
		while(!queue.isEmpty()) {
			Bridge b = queue.poll();
			if(union(b.from, b.to)) {
				result += b.len;
				cnt++;
			}
			if(cnt == CNT-1) {
				System.out.println(result);
				return;
			}
		}
		System.out.println(-1);
	}
	
	private static void dfs(int r, int c) {
		map[r][c] = CNT;
		for(int d=0; d<4; d++) {
			int nr = r + D[0][d];
			int nc = c+ D[1][d];
			if(isOutOfRange(nr, nc) || map[nr][nc] != NOT_VISITED) continue;
			dfs(nr, nc);
		}
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
