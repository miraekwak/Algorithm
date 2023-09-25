package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제]
 * 빈칸, 벽, 바이러스가 주어질 때
 * 바이러스는 상하좌우로 벽을 만날때까지 번짐
 * 바이러스가 존재하지 않는 안전영역의 최대값을 구해라
 * 
 * 입력]
 * NxM
 * 0 빈칸, 1 벽, 2 바이러스
 * 3<=N, 8<=8
 * 2<= 바이러스 <= 10
 * 3<= 빈칸
 * 
 * 알고리즘]
 * dfs를 사용한 완전탐색
 * 1. 빈칸 중 3개의 칸 선택 64(8*8)-2(바이러스최소) = 62
 * 62C3 = 62 * 61 * 60 = 226920
 * 2. 선택된 칸을 벽으로 표시
 * 3. 바이러스 위치부터 시작해서 바이러스 번짐 계산
 * 4. 빈칸 계산 및 갱신
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_14502_연구소 {

	static int N, M, map[][], temp[][], selected[], MAX_SPACE;
	static boolean visited[][];
	static List<Virus> viruses;
	static List<Integer> space;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static class Virus{
		int r, c;
		public Virus(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		space = new ArrayList<Integer>();
		viruses = new ArrayList<Virus>();
		map = new int[N][M];
		temp = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				temp[i][j] = Integer.parseInt(st.nextToken());
				if(temp[i][j] == 0) space.add(i*M+j);
				else if(temp[i][j] == 2) viruses.add(new Virus(i, j));
			}
		}
		selected = new int[3];
		visited = new boolean[N][M];
		
		combi(0, 0);
		System.out.println(MAX_SPACE);
	}
	
	private static void combi(int cnt, int str) {
		if(cnt == 3) {
			copy();
			setWall(selected[0], selected[1], selected[2], 1);
			MAX_SPACE = Math.max(MAX_SPACE, getSpace());
			setWall(selected[0], selected[1], selected[2], 0);
			return;
		}
		
		for(int i=str; i<space.size(); i++) {
			selected[cnt] = space.get(i);
			combi(cnt+1, i+1);
		}
	}

	private static void setWall(int a, int b, int c, int val) {
		map[a/M][a%M] = map[b/M][b%M] = map[c/M][c%M] = val;
	}
	
	private static void copy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(temp[i], 0, map[i], 0, M);
			Arrays.fill(visited[i], false);
		}
	}
	
	private static int getSpace() {
		for(Virus v: viruses) {
			dfs(v.r, v.c);
		}
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	private static void dfs(int r, int c) {		
		map[r][c] = 2;
		visited[r][c] = true;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(!isInRange(nr, nc) || map[nr][nc] == 1 || visited[nr][nc]) continue;
			dfs(nr, nc);
		}
	}
	
	private static boolean isInRange(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
