package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제]
 * 1<= 충전기(BC)의 개수 A <= 8
 * 사용자 2명
 * 동시간대 같은 충전기 접속 시 충전량을 반으로 나눠가짐
 * 이동방향 정지 상 우 하 좌
 * 
 * 출력]
 * 두 사용자가 충전한 충전량의 최댓값 계산
 * 
 * 알고리즘]
 * 1. map 표현
 * 	- 10 x 10 x A(최대 8) 의 3차원 배열 사용 
 * 	- 3차원은 각 배터리의 범위에 포함되었는지 여부
 *  - 충전기 정보에 대해 각각 범위 계산 -> BFS
 * 2. M 이동시간만큼 탐색 (20<=M<=100)
 * 	- 각 위치에서의 가능한 충전기 확인
 * 	- 두 사용자의 충전기가 같을 경우 둘 중 한쪽의 충전기 변경 후 확인
 * 	- 충전기가 같다면 배터리 나눠 가지기
 *  - 아니라면 각각 배터리 충전
 * 	
 *
 * @author SSAFY
 *
 */
public class Solution_5644_무선충전_리팩토링 {
	
	static class Charger implements Comparable<Charger>{
		int num, x, y, c, p;
		public Charger(int n, int x, int y, int c, int p) {
			this.num = n;
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		@Override
		public int compareTo(Charger o) {
			return Integer.compare(o.p, this.p);
		}
	}

	static int M, A;
	static int moveA[], moveB[];
	static Charger[] chargers;
	static boolean map[][];
	static int[] dy = {0, -1, 0, 1, 0}; // 행 
	static int[] dx = {0, 0, 1, 0, -1}; // 열  정지-상-우-하-좌
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());

		StringTokenizer st;
		int x, y, c, p;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			moveA = new int[M+1];
			moveB = new int[M+1];
			// A 이동 입력
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			// B 이동 입력
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			// 충전기 정보 입력
			chargers = new Charger[A];
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				chargers[i] = new Charger(i, x-1, y-1, c, p);
			}
		
			// 정렬 후 위치 업데이트
			Arrays.sort(chargers);
			for(int i=0; i<A; i++) {
				chargers[i].num = i;
			}
			
			map = new boolean[10][10];
			markMap();
			sb.append("#"+t+" "+charge()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void markMap() {
		for(Charger charger: chargers) {
			bfs(charger);
		}
	}
	
	private static void bfs(Charger charger) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{charger.y, charger.x});
		map[charger.y][charger.x] = true;
		
		int depth = charger.c;
		while(!queue.isEmpty() && depth-- > 0) {
			int size = queue.size();
			while(size-- > 0) {
				int[] curr = queue.poll();
				for(int d=1; d<=4; d++) {
					int ny = curr[0]+dy[d];
					int nx = curr[1]+dx[d];
					if(!isInRange(ny, nx)) continue;
					map[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}
			}
		}
	}
	
	private static int charge() {
		int total = 0;
		int ay = 0, ax = 0;
		int by = 9, bx = 9;
		
		Charger[] chargersA = new Charger[2];
		Charger[] chargersB = new Charger[2];		
		int asize=0, bsize=0;

		for(int time = 0; time<=M; time++) {
			// A와 B 이동
			ay += dy[moveA[time]];
			ax += dx[moveA[time]];
			by += dy[moveB[time]];
			bx += dx[moveB[time]];
			
			if(map[ay][ax] && map[by][bx]) {
				asize = bsize = 0;
				for(int i=0; i<A; i++) {
					if(asize >= 2) break;
					if(distance(ay, ax, chargers[i].y, chargers[i].x) <= chargers[i].c) {
						chargersA[asize++] = chargers[i];						
					}
				}
				for (int i = 0; i < A; i++) {
					if(bsize >= 2) break;
					if(distance(by, bx, chargers[i].y, chargers[i].x) <= chargers[i].c) {
						chargersB[bsize++] = chargers[i];						
					}
				}
				
				// A와 B의 최대 충전량이 다를 경우
				if(chargersA[0].num != chargersB[0].num) {
					total += chargersA[0].p + chargersB[0].p;
					continue;
				}
				
				// A와 B의 최대 충전량이 같을 경우 
				total += chargersA[0].p; // 최대 충전량이 같기 때문에 일단 하나를 더함
				if(asize-1 > 0 && bsize-1 > 0) { // 둘다 후보 충전기가 있을 때 2번째로 큰 충전량 중 더 큰 값을 더함 
					total += Math.max(chargersA[1].p, chargersB[1].p);
				}
				else if(asize-1 > 0) { // A만 있을 경우 A의 후보 충전량을 더함
					total += chargersA[1].p;
				}
				else if(bsize-1 > 0) { // B만 있을 경우 B의 후보 충전량을 더함
					total += chargersB[1].p; 
				}
			}
			else if(map[ay][ax]) {
				for(Charger charger: chargers) {
					if(distance(ay, ax, charger.y, charger.x) > charger.c) continue;
					total += charger.p;
					break;
				}
			}
			else if(map[by][bx]) {
				for(Charger charger: chargers) {
					if(distance(by, bx, charger.y, charger.x) > charger.c) continue;
					total += charger.p;
					break;
				}
			}
		}
		return total;
	}
	
	private static int distance(int r, int c, int y, int x) {
		return Math.abs(r - y) + Math.abs(c - x);
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && r<10 && c>=0 && c<10;
	}
}

