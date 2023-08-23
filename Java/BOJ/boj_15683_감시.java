package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * K개의 씨씨티비
 * 1~5번까지의 경우의 수
 * 
 * 
 * 알고리즘]
 * CCTV 위치 구하기
 * 
 * CCTV 감시영역 구하기 - dfs
 * 번호에 따라 볼 수 있는 방식에 대해 dfs 재귀 적용
 * - CCTV가 볼수 있는 곳에 표시
 * - 존재하는 CCTV에 대해 적용 완료 시 최소 크기 구하기
 * 
 * 사각지대 최소 크기 구하기 - bfs
 * 최소 크기 구하기
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_15683_감시 {

	private static int N, M, cctvCnt, rooms;
	private static int map[][], temp[][], selected[];
	private static CCTV[] cctvs;
	private static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	private static int[] dc = { 0, 1, 0, -1 }; // 상우하좌
	
	static class CCTV { // 시작 위치, 끝위치 저장을 위한 클래스
		int num;
		int r; // 행 위치
		int c; // 열 위치

		public CCTV(int n, int r, int c) {
			this.num = n;
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 

		map = new int[N][M];
		temp = new int[N][M];
		cctvs = new CCTV[8];
		rooms = N*M;
		cctvCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int c = Integer.parseInt(st.nextToken());
				if(c == 0 || c==6) temp[i][j] = c; // 0:빈공간, 1:못가는 공간, 6:벽
				else {
					temp[i][j] = 1;
					cctvs[cctvCnt++] = new CCTV(c, i, j);
				}
			}
		}
		selected = new int[cctvCnt];
		permu(0);
		System.out.println(rooms);
	}
	
	private static void permu(int cnt){
		if(cnt == cctvCnt) {
			copy(temp, map);
			monitor();
			rooms = Math.min(rooms, countRoom());
			return;
		}
		
		switch(cctvs[cnt].num) {
		case 1: case 3: case 4: 
			for(int d=0; d<4; d++) {
				selected[cnt] = d;
				permu(cnt+1);
			}
			break;
		case 2:
			for(int d=0; d<2; d++) {
				selected[cnt] = d;
				permu(cnt+1);
			}
			break;
		case 5:
			selected[cnt] = 0;
			permu(cnt+1);
		}
	}
	
	private static void monitor() {
		for(int i=0; i<cctvCnt; i++) {
			CCTV tv = cctvs[i];
			switch(tv.num) {
			case 1:
				cctv(selected[i], tv.r, tv.c);
				break;
			case 2:
				cctv(selected[i], tv.r, tv.c);
				cctv(selected[i]+2, tv.r, tv.c);
				break;
			case 3:
				cctv(selected[i], tv.r, tv.c);
				cctv((selected[i]+1)%4, tv.r, tv.c);
				break;
			case 4:
				cctv(selected[i], tv.r, tv.c);
				cctv((selected[i]+1)%4, tv.r, tv.c);
				cctv((selected[i]+2)%4, tv.r, tv.c);
				break;
			case 5:
				cctv(0, tv.r, tv.c);
				cctv(1, tv.r, tv.c);
				cctv(2, tv.r, tv.c);
				cctv(3, tv.r, tv.c);
				break;
			}
		}

	}

	private static void cctv(int d, int r, int c) {
		int i=r, j=c;
		while(true) {
			if(!isInRange(i, j) || map[i][j] == 6) break;
			map[i][j] = 1;
			i += dr[d];
			j += dc[d];
		}
	}
	
	private static int countRoom() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	private static void copy(int[][] src, int[][] dst) {
		for(int i=0; i<N; i++) {
			System.arraycopy(src[i], 0, dst[i], 0, M);
		}
	}
}
