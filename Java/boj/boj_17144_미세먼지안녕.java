package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제]
 * RxC
 * 공기청정기 1열 - 크기 두행 2x1
 * 1초
 * 1. 미세먼지 확산
 * - r,c에 있는 미세먼지 인접합 4방향 확산
 * - 인접 방향에 공기청정기가 있거나, 칸이 없으면 확산x
 * - 확산양 A/5 소수점 제외
 * - r,c에 남은 미세먼지 양은 A-(A/5)*(확산된 방향의 개수)
 * 2. 공기청정기 작동
 * - 위쪽 공기청정기 : 반시계방향 순환, 아래쪽 공기청정기 : 시계방향 순환
 * - 미세먼지가 바람의 방향대로 한칸씩 이동
 * 
 * T초가 지난 후 방에 남아있는 미세먼지 양 계산
 * 
 * 입력]
 * 
 * 
 * 알고리즘]
 * T초만큼 실행
 * 1. 확산함수
 * 원본배열 + 확산 값 = 확산된 배열
 * 현재 위치에서 상,하,좌,우 확산
 * 확산값 
 * - 상하좌우로 확산되는 값은 +
 * - 현재의 미세먼지 값은 -
 * 
 * 2. 공기청정기 함수
 * 위쪽 아래쪽으로 나누기
 * 두 방향에 따라 값 당기기
 * 
 * @author SSAFY
 *
 */
public class boj_17144_미세먼지안녕 {

	static int R, C, T, map[][], origin[][], cleaner;
	static int[] dr = {-1, 0, 1, 0}; // 시계방향 - 상 우 하 좌
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		origin = new int[R][C];
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				if(origin[i][j] == -1) cleaner = i;
			}
		}
		
		System.out.println(getRemain());
	}

	private static int getRemain() {
		
		for(int t=1; t<=T; t++) {
			spread();
			clear();
		}
		
		int total = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(origin[i][j] > 0) total+=origin[i][j];
			}
		}
		return total;
	}

//	private static void print() {
//		for(int i=0; i<R; i++) {
//			for(int j=0; j<C; j++) {
//				System.out.print(origin[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}

	private static void spread() {
		int cnt, num;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(origin[i][j] <= 0) continue;
				cnt = 0;
				num = origin[i][j]/5;
				for(int d=0; d<4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(isOutOfRange(nr, nc) || origin[nr][nc] ==-1) continue;
					map[nr][nc] += num;
					cnt++;
				}
				map[i][j] -= num*cnt;
			}
		}
		
		// 확산 이후 미세먼지 양 구하기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(origin[i][j] == -1) continue;
				origin[i][j] += map[i][j];
				map[i][j] = 0;
			}
		}
	}
	
	private static void clear() {
		// 위쪽 반시계방향
		cycle(cleaner-1, 0, 1);
		// 아래쪽 시계방향
		cycle(cleaner, 2, -1);
	}
	
	/**
	 * 위, 아래에 따라 값을 당겨오기
	 * @param str_r : 청소기 위치
	 * @param d : 시작 방향 (위:0, 아래:2)
	 * @param direc : 값을 당겨오는 방향(위 : 1, 아래: -1)
	 */
	private static void cycle(int str_r, int d, int direc) {
		int r, c;
		r = str_r - direc; 
		c = 0;
		while(r != str_r || c != 1) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(isOutOfRange(nr, nc) || (direc==1 && nr > str_r) || (direc==-1 && nr < str_r)) {
				d += direc;
				if(d < 0) d = 3;
				if(d > 3) d = 0;
				continue;
			}
			origin[r][c] = origin[nr][nc];
			r = nr;
			c = nc;
		}
		origin[r][c] = 0;
	}
	
	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
}
