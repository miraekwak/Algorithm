package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고리즘]
 * bfs를 통한 완전 탐색?
 * 키로 인해 왔던 곳을 또가야하는 경우가 발생 -> 어떻게 하지?
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_1194_달이차오른다가자 {

	static int N, M, dr[]= {1, -1, 0, 0}, dc[]= {0, 0, 1, -1};
	static char map[][];
	static boolean visited[][][];
	static Point start;
	static class Point {
		int r, c, key;
		public Point(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		visited = new boolean[N][M][(1<<6)];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == '0')  start = new Point(i, j, 0);
			}
		}
		
		System.out.println(findRoad());
	}
	private static int findRoad() {		
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(start);
		visited[start.r][start.c][start.key] = true;

		int day = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Point curr = queue.poll();

				if(map[curr.r][curr.c] == '1') return day;
				
				for(int d=0; d<4; d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					int nkey = curr.key;
					if(isOutOfRange(nr, nc) || map[nr][nc] == '#' || visited[nr][nc][nkey]) continue;
					if('A' <= map[nr][nc] && map[nr][nc] <= 'F') { // 문이 있다면 키가 있는지 확인
						int k = map[nr][nc] - 'A';
						if((curr.key & (1<<k)) == 0) continue; // 키가 없으면 컨티뉴
					}
					if('a' <= map[nr][nc] && map[nr][nc] <= 'f') { // 키가 있다면 키를 세팅
						int k = map[nr][nc] - 'a';
						nkey = curr.key |(1<<k);
					}
					
					// 빈칸, 출발지, 도착지, 키가 있는 문일경우
					visited[nr][nc][nkey] = true;
					queue.add(new Point(nr, nc, nkey));
				}
			}
			day++;
		}
		
		return -1;
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}

