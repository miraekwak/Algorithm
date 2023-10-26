package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고리즘]
 * 고슴도치부터 bfs
 * 물 가장자리 구하기
 * - 물 녹이기
 * - 고슴도치 이동
 * 
 * @author SSAFY
 *
 */
public class boj_3055_탈출 {

	static int R, C, WATER = 2, NOT_GO = 3, GO = 0, DST=1;
	static int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};
	static int visited[][];
	static Point S, D;
	static Queue<Point> waters;
	
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		visited = new int[R][C];
		waters = new ArrayDeque<Point>();
		for(int i=0; i<R; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				switch(line[j]) {
				case 'X':
					visited[i][j] = NOT_GO;
					break;
				case '*':
					waters.add(new Point(i, j));
					visited[i][j] = WATER;
					break;
				case 'D':
					D = new Point(i, j);
					visited[i][j] = DST;
					break;
				case 'S':
					S = new Point(i, j);
					visited[i][j] = DST;
					break;
				}
			}
		}
		
		System.out.println(getMinTime());
	}
	
	public static String getMinTime() {
		Queue<Point> dochi = new ArrayDeque<Point>();
		dochi.add(S);
		int time = 0;
		while(!dochi.isEmpty()) {
			int size;
			size = waters.size();
			while(size-->0) {
				Point curr = waters.poll();
				for(int d=0; d<4; d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					if(isOutOfRange(nr, nc) || visited[nr][nc] != GO) continue;
					visited[nr][nc] = WATER;
					waters.add(new Point(nr, nc));
				}				
			}
			
			size = dochi.size();
			while(size-->0) {
				Point curr = dochi.poll();
				if(curr.r == D.r && curr.c == D.c) return Integer.toString(time);
				
				for(int d=0; d<4; d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					if(isOutOfRange(nr, nc) || visited[nr][nc] > DST) continue;
					visited[nr][nc] = NOT_GO;
					dochi.add(new Point(nr, nc));
				}
			}
			time++;
		}
		return "KAKTUS";
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
	
	static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(visited[i][j] == WATER?"*":".");
			}
			System.out.println();
		}
		System.out.println();
	}
}

