package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class Solution_5644_무선충전 {
	
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
			moveA = new int[M];
			moveB = new int[M];
			// A 이동 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			// B 이동 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
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
	
	private static void print() {

		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				System.out.print(map[i][j]?"■ ":"□ ");
			}
			System.out.println();
		}
		System.out.println();
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
		int time = 0;
		List<Charger> chargersA, chargersB;
		while(true) {
			if(map[ay][ax] && map[by][bx]) {
				chargersA = new ArrayList<>();
				chargersB = new ArrayList<>();
				
				for(int i=0; i<A; i++) {
					if(distance(ay, ax, chargers[i].y, chargers[i].x) <= chargers[i].c) {
						chargersA.add(chargers[i]);						
					}
					if(distance(by, bx, chargers[i].y, chargers[i].x) <= chargers[i].c) {
						chargersB.add(chargers[i]);						
					}
				}
				if(chargersA.get(0).num == chargersB.get(0).num) {
					if(chargersA.size()-1 > 0 && chargersB.size()-1 > 0) {
						if(chargersA.get(1).p > chargersB.get(1).p) {
							total += chargersA.get(1).p + chargersB.get(0).p;
						}
						else {
							total += chargersA.get(0).p + chargersB.get(1).p;
						}
					}
					else if(chargersA.size()-1 > 0) {
						total += chargersA.get(1).p + chargersB.get(0).p;
					}
					else if(chargersB.size()-1 > 0) {
						total += chargersA.get(0).p + chargersB.get(1).p;
					}
					else {
						total += chargersA.get(0).p;
					}
				} else {
					total += chargersA.get(0).p + chargersB.get(0).p;
				}
			}
			else if(map[ay][ax]) {
				int max = 0;
				for(Charger charger: chargers) {
					if(distance(ay, ax, charger.y, charger.x) <= charger.c) max = Math.max(max, charger.p);
				}
				total += max;
			}
			else if(map[by][bx]) {
				int max = 0;
				for(Charger charger: chargers) {
					if(distance(by, bx, charger.y, charger.x) <= charger.c) max = Math.max(max, charger.p);
				}
				total += max;
			}
//			System.out.println(time + " : "+total+" a: ("+ay+", "+ax+") b: "+by+", "+bx+")");
			if(time == M) break;
			ay += dy[moveA[time]];
			ax += dx[moveA[time]];
			by += dy[moveB[time]];
			bx += dx[moveB[time]];
			time++;
		}
//		System.out.println("A : "+a_total+" B : "+b_total);
		return total;
	}
	
	private static int distance(int r, int c, int y, int x) {
		return Math.abs(r - y) + Math.abs(c - x);
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=0 && r<10 && c>=0 && c<10;
	}
}

