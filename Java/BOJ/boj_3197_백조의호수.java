package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제]
 * R x C 행의 직사각형 모양
 * . 물, X 빙판, L 백조
 * 하루가 지나면 물과 접촉한 빙판은 녹음(상하좌우)
 * 몇일이 지나면 두 백조가 만날수 있나
 * 
 * 입력]
 * 1<= R, C <= 1500
 * 
 * 출력]
 * 두 백조가 만나기까지 걸리는 날
 * 
 * 알고리즘]
 * bfs를 사용한 완전 탐색
 * - bfs의 한단계마다 dfs를 통해 두 백조가 만나는지 탐색
 * 
 * 
 */
public class boj_3197_백조의호수 {
	
	static int R, C, dr[]= {1, -1, 0, 0}, dc[]= {0, 0, 1, -1};
	static int visited[][];
	static boolean isWater[][], isTouch;
	static Swan start, end;
	static class Swan {
		int r, c, num;
		public Swan(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isWater = new boolean[R][C];
		visited = new int[R][C];
		for(int i=0; i<R; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				char c = line[j];
				if(c == '.') isWater[i][j] = true;
				else if(c == 'X') isWater[i][j] = false;
				else if(c == 'L') {
					if(start == null) start = new Swan(i, j, 1);
					else end = new Swan(i, j, 2);
					isWater[i][j] = true;
				}
			}
		}
		
		System.out.println(getDay());
	}
	
	private static int getDay() {
		int day = 0;
		
		Queue<int[]> waters = getEdges();
		Queue<Swan> swans = getSwans();
		Queue<Swan> temp;
		while(!isTouch && !waters.isEmpty()) {
			day++;
			int size = waters.size();
			while(size-- > 0) {
				int[] curr = waters.poll();
				// 주위에 빙판인 경우 녹이고 해당 위치 저장하기
				for(int d=0; d<4; d++) {
					int nr = curr[0]+dr[d];
					int nc = curr[1]+dc[d];
					// 범위안에 없거나, 이미 방문했거나, 옆이 물인 경우
					if(!isInRange(nr, nc) || isWater[nr][nc]) continue;
					isWater[nr][nc] = true;
					waters.add(new int[] {nr, nc});
				}
			}
			size = swans.size();
			temp = new ArrayDeque<Swan>();
			while(!swans.isEmpty()) {
				Swan curr = swans.poll();
				
				for(int d=0; d<4; d++) {
					int nr = curr.r+dr[d];
					int nc = curr.c+dc[d];
					if(!isInRange(nr, nc)) continue;
					if(!isWater[nr][nc]) {
						temp.add(curr);
						continue;
					}
					if(visited[nr][nc] == curr.num) continue; // 자신이 지나온 길이면 넘어가기
					if(visited[nr][nc] != 0) { // 두 백조가 만났다면 리턴
						isTouch = true;
						break;
					}
					visited[nr][nc] = curr.num;
					swans.add(new Swan(nr, nc, curr.num));
					isTouch = isExistNear(new Swan(nr, nc, curr.num));
				}
				if(isTouch) break;
			}
			swans = temp;
		}
		return day;
	}

	private static boolean isExistNear(Swan swan) {
		for(int d=0; d<4; d++) {
			int nr = swan.r+dr[d];
			int nc = swan.c+dc[d];
			if(!isInRange(nr, nc) || visited[nr][nc] == 0) continue;
			if(visited[nr][nc] != swan.num) return true;
		}
		return false;
	}
	
	private static Queue<int[]> getEdges() {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				// 빙판과 접촉한 물 가장자리 구하기
				if(!isWater[i][j]) continue; // 빙판인경우
				boolean isExist = false;
				for(int d=0; d<4; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if(!isInRange(nr, nc) || isWater[nr][nc]) continue;
					isExist = true; // 주위에 빙판이 있는 경우
					break;
				}
				if(isExist) queue.add(new int[] {i, j});
			}
		}
		return queue;
	}
	
	private static Queue<Swan> getSwans() {
		Queue<Swan> swans = new ArrayDeque<>();
		Queue<Swan> queue = new ArrayDeque<Swan>();
		queue.add(start);
		queue.add(end);
		visited[start.r][start.c] = start.num;
		visited[end.r][end.c] = end.num;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Swan curr = queue.poll();
				for(int d=0; d<4; d++) {
					int nr = curr.r+dr[d];
					int nc = curr.c+dc[d];
					if(!isInRange(nr, nc)) continue;
					if(!isWater[nr][nc]) {
						swans.add(curr);
						continue;
					}
					if(visited[nr][nc] == curr.num) continue; // 자신이 지나온 길이면 넘어가기
					if(visited[nr][nc] != 0) { // 두 백조가 만났다면 리턴
						isTouch = true;
						return swans;
					}
					// 범위안에 있고, 물이고
					visited[nr][nc] = curr.num;
					queue.add(new Swan(nr, nc, curr.num));
				}
			}
		}
		return swans;
	}
	
	private static boolean isInRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
