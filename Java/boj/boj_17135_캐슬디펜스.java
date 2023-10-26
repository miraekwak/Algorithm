package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxM
 * N번행 바로 아래(N+1번행)의 모든칸에 성
 * 궁수 3명 배치
 * 	- 궁수는 성이 있는 칸에 배치
 * 	- 하나의 칸에 궁수 한명
 * 	- 궁수는 적하나 공격
 * 	- 모든 궁수는 동시 공격
 *  - D이하인 적 중 가장 가까운 적
 *  - 여럿일 경우 가장 왼쪽 적 공격
 *  - 같은 적이 여러 궁수한테 공격받을 수 있음
 *  - 공격받은 적은 게임 제외
 * 궁수 공격 끝나고 적 이동
 *  - 적은 아래로 한칸 이동
 *  - 성이 있는 칸으로 이동시 게임에서 제외
 * 모든 적 제거시 게임 종료
 *  
 * 궁수 위치가 정해지면 게임은 동일 -> 궁수 위치 중요
 * 
 *  
 * 출력]
 * 궁수의 공격으로 제외할 수 있는 적의 최대 수
 * 
 * 알고리즘]
 * - 적은 턴마다 아래로 한칸 이동하기 때문에 앞에 있는 적부터 제거 => BFS
 * - 적이 여럿일 경우 가장 왼쪽에 있는 적 -> 2차원 배열에서 인덱스 작은 곳부터 탐색
 * 
 * 1. 궁수 3명 배치 -> M개의 열 중 3개 선택하는 조합
 * 2. 각 궁수 위치에서 거리만족하는 적 제거 -> bfs
 * 		- 가장 가까운 적 제거 
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_17135_캐슬디펜스 {
	
	private static int N, M, D, ENEMY_NUM;
	private static boolean visited[][];
	private static int map[][], saved[][];
	private static int[] selected;
	private static int[] dr = {0, -1, 0}; // 좌 상 우 // 왼쪽부터 탐색하기 위함
	private static int[] dc = {-1, 0, 1}; // 좌 상 우
	
	private static StringBuilder sb;
	
	static class Enemy{
		int r;
		int c;
		public Enemy(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];	
		saved = new int[N][M];	
		visited = new boolean[N][M];		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				saved[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new int[3];
		ENEMY_NUM = 0;
		sb = new StringBuilder();
		combi(0, 0);
		System.out.println(ENEMY_NUM);
	}
	
	private static void combi(int cnt, int str) {
		if(cnt == 3) {
//			System.out.println(Arrays.toString(selected));
			ENEMY_NUM = Math.max(ENEMY_NUM, getMaxEnemy());
			return;
		}
		
		for(int i=str; i<M; i++) {
			selected[cnt] = i;
			combi(cnt+1, i+1);
		}
	}
	
	private static int getMaxEnemy() {
		int cnt = 0;
		copy();
		do {
			for(int i=0; i<3; i++) {
				findEnemy(N-1 ,selected[i]);
				cnt += getKillCnt();
			}			
		} while(isExistEnemy());
		return cnt;
	}
	
	private static boolean findEnemy(int r, int c) {
		Queue<Enemy> queue = new ArrayDeque<>();
		queue.offer(new Enemy(r, c));
		
		makeVistitedArrToFalse();
		visited[r][c] = true;
		
		int dis = 1;
		while(!queue.isEmpty() && dis++ <= D) {
			int size = queue.size();
			while(size-- > 0) {
				Enemy e = queue.poll();
				if(map[e.r][e.c] != 0) {
					map[e.r][e.c] = -1;
					return true;
				}
				for(int d=0; d<3; d++) {
					int nr = e.r + dr[d];
					int nc = e.c + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					queue.offer(new Enemy(nr, nc));
				}
			}
		}
		return false;
	}

	private static boolean isExistEnemy() {
		boolean isExist = false;
		for(int j=0; j<M; j++) {
			map[N-1][j] = 0;
		}
		for(int i=N-2; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					map[i+1][j] = 1;
					map[i][j] = 0;
					isExist = true;
				}
			}
		}		
		return isExist;
	}
	
	private static int getKillCnt() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -1) {
					map[i][j] = 0;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static void copy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(saved[i], 0, map[i], 0, M);
		}
	}
	
	private static void makeVistitedArrToFalse() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
