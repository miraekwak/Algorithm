package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * R x C 7가지 블럭 | : 수직 - : 수평 + : 수직 & 수평 1 : 하 + 우 2 : 상 + 우 3 : 상 + 좌 4 : 하 +
 * 좌 M : 출발지점 Z : 도착지점 . : 빈칸
 * 
 * 출력] 지워진 칸과 블록
 * 
 * 알고리즘]
 * 비트마스킹
 * 
 * | - + 1 2 3 4 
 * 0 1 2 3 3 5 6
 * 
 * 4 3 2 1 + - |
 * 6 5 4 3 2 1 0
 * 
 * 상 연결 정보
 * 1 0 0 1 1 0 1
 * 우 연결 정보
 * 1 1 0 0 1 1 0
 * 하 연결 정보
 * 0 1 1 0 1 0 1
 * 좌 연결 정보
 * 0 0 1 1 1 1 0
 * 
 * @author SSAFY
 *
 */
public class boj_2931_가스관_2 {

	private static int R, C;
	private static char[][] map;
	private static Location start;
	private static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	private static int[] dc = { 0, 1, 0, -1 }; // 상우하좌
	private static int[] connection = {0b1001101, 0b1100110, 0b0110101, 0b0011110};
	private static int[] index;
	private static StringBuilder sb;

	static class Location { // 시작 위치, 끝위치 저장을 위한 클래스
		int r; // 행 위치
		int c; // 열 위치

		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception, IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 행 열 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열

		map = new char[R][]; // 도면 정보 저장 배열

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'M') { // M 시작 위치 일경우
					start = new Location(i, j); // 시작 위치 저장
				}
			}
		}
		index = new int[125];
		index['|'] = 0;
		index['-'] = 1;
		index['+'] = 2;
		index['1'] = 3;
		index['2'] = 4;
		index['3'] = 5;
		index['4'] = 6;
		
		findRoad();
		System.out.println(sb);
	}

	private static void findRoad() {
		int d = getStartDirection(); // 바라보고 있는 방향 : 상우하좌

		int r = start.r, c = start.c; // 탐색 시작 위치

		// 주어진 도면의 길을 따라 가면서 비어있는 공간 찾기
		do {
			if (map[r][c] == '.') { // 길이 빈 공간이라면 멈춤
				break;
			}

			if (map[r][c] == '1') { // 1 일경우 현재 방향에 따라 방향 전환
				if (d % 2 == 0) d = 1;
				else d = 2;
			} else if (map[r][c] == '2') { // 2 일경우 현재 방향에 따라 방향 전환
				if (d % 2 == 0) d = 1;
				else d = 0;
			} else if (map[r][c] == '3') { // 3 일경우 현재 방향에 따라 방향 전환
				if (d % 2 == 0) d = 3;
				else d = 0;
			} else if (map[r][c] == '4') { // 4일 경우 현재 방향에 따라 방향 전환
				if (d % 2 == 0) d = 3;
				else d = 2;
			}
			r += dr[d];
			c += dc[d];
		} while (true);

		// 빈공간 위치 r, c
		sb.append((r + 1) + " " + (c + 1) + " ");
		
		// 빈 공간에 대해 알맞은 블록 찾기
		int connectionInfo = getConnection(r, c, d); // 0000 -> 좌하우상
		// 빈공간 기준 상, 하, 좌, 우 길일 경우 + 1111
		if (connectionInfo == 15) sb.append("+");
		// 빈공간 기준 상, 하 길일 경우 | 0101
		else if (connectionInfo == 5) sb.append("|");
		// 빈공간 기준 좌, 우가 길일 경우 - 1010
		else if (connectionInfo == 10) sb.append("-");
		// 빈공간 기준 하, 우가 길일 경우 1 0110
		else if (connectionInfo == 6) sb.append("1");
		// 빈공간 기준 상, 우가 길일 경우 2 0011
		else if (connectionInfo == 3) sb.append("2");
		// 빈공간 기준 상, 좌가 길일 경우 3 1001
		else if (connectionInfo == 9) sb.append("3");
		// 빈공간 기준 하, 좌가 길일 경우 4 1100
		else if (connectionInfo == 12) sb.append("4");
	}
	
	private static int getConnection(int r, int c, int d) {
		int flag = 0; // 0000 -> 좌하우상
		// 빈 공간에 대해 상하좌우 연결요소 찾기
		for (d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(!isInRange(nr, nc)) continue;
			if(map[nr][nc] == '.' || map[nr][nc] == 'Z' || map[nr][nc] == 'M') continue;
			if(isConnected(nr, nc, d)) {
				flag = flag | (1<<d);
			}
		}
		return flag;
	}
	
	private static boolean isConnected(int r, int c, int d) {
		if((connection[d] & 1<<index[map[r][c]]) == 0) return false;
		return true;
	}
	
	private static int getStartDirection() {
		int r = start.r, c = start.c; // 탐색 시작 위치

		// 처음 방향 설정
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(!isInRange(nr, nc)) continue;
			if (map[nr][nc] == '.' || map[nr][nc] == 'M' || map[nr][nc] == 'Z') continue; // 범위체크
			if(isConnected(nr, nc, d)) {
				return d;
			}
		}
		return -1;
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
