package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 문제]
 * 5x5
 * 소문난칠공주 구성
 * - 7명 여학생
 * - 가로나 세로로 인접
 * - 이다솜파 + 임도연파
 * - 이다솜파 4명 이상
 * 경우의 수 구하기
 * 
 * 입력]
 * S - 이다솜파
 * Y - 임도연파
 * 
 * 알고리즘] - BFS
 * 25개 중에 7개를 뽑는 조합
 * 완성된 조합에 대해 칠공주 확인
 * - BFS로 연결여부 판단
 * - 개수세기
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_1941_소문난칠공주 {

	static char map[][];
	static boolean visited[];
	static int selected[], CNT;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][];
		selected = new int[7];
		visited = new boolean[7];
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		combi(0, 0);
		System.out.println(CNT);
	}
	
	private static void combi(int cnt, int str) {
		if(cnt == 7) {
			if(isSevenPrincess()) CNT++;
			return;
		}
		
		for(int i=str; i<25; i++) {
			selected[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

	private static boolean isSevenPrincess() {
		int scnt = 0;
		Arrays.fill(visited, false);
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(selected[0]);
		visited[0] = true;

		while(!queue.isEmpty()) {
			int curr = queue.poll();
			int r = curr/5;
			int c = curr%5;
			
			if(map[r][c] == 'S') scnt++;
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				int num = nr*5+nc;
				if(isOutOfRange(nr, nc)) continue;
				for(int i=1; i<7; i++) {
					if(visited[i]) continue;
					if(selected[i] == num) {
						visited[i] = true;
						queue.add(selected[i]);
					}
				}
			}
		}
		
		// 모든 요소가 연결되어 있는지 검사
		for(int i=0; i<7; i++) {
			if(!visited[i]) return false;
		}
		
		// S가 4개 이상인지 검사
		if(scnt < 4) return false;
		
		return true;
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= 5 || c < 0 || c >= 5;
	}
}
