package boj;

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
 * @author SSAFY
 *
 */
public class boj_2931_가스관 {

	private static int R, C;
	private static char[][] map;
	private static Location start, end;
	private static int[] dr = { -1, 0, 1, 0 }; // 상우하좌
	private static int[] dc = { 0, 1, 0, -1 }; // 상우하좌
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
				else if (map[i][j] == 'Z') { // Z : 끝 위치일 경우
					end = new Location(i, j); // 끝 위치 저장
				}
			}
		}
		findRoad();
		System.out.println(sb);
	}

	private static void findRoad() {
		int d = getStartDirection(); // 바라보고 있는 방향 : 상우하좌
		if(d == -1) {
			swapStartAndEnd();
			d = getStartDirection();
		}
		
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
		} while (r != end.r || c != end.c); // 종료 위치에 도달할 때까지

		// 빈공간 위치 r, c
		sb.append((r + 1) + " " + (c + 1) + " ");
		
		// 빈 공간에 대해 알맞은 블록 찾기
		boolean[] isConnected = getConnection(r, c, d);
		if (isConnected[0] && isConnected[1] && isConnected[2] && isConnected[3]) { // 빈공간 기준 상, 하, 좌, 우 길일 경우 +
			sb.append("+");
		} else if (isConnected[0] && isConnected[2]) { // 빈공간 기준 상, 하 길일 경우 |
			sb.append("|");
		} else if (isConnected[1] && isConnected[3]) { // 빈공간 기준 좌, 우가 길일 경우 -
			sb.append("-");
		} else if (isConnected[2] && isConnected[1]) { // 빈공간 기준 하, 우가 길일 경우 1
			sb.append("1");
		} else if (isConnected[0] && isConnected[1]) { // 빈공간 기준 상, 우가 길일 경우 2
			sb.append("2");
		} else if (isConnected[0] && isConnected[3]) { // 빈공간 기준 상, 좌가 길일 경우 3
			sb.append("3");
		} else if (isConnected[2] && isConnected[3]) { // 빈공간 기준 하, 좌가 길일 경우 4
			sb.append("4");
		}
	}
	
	private static boolean[] getConnection(int r, int c, int d) {
		boolean[] isConnected = new boolean[4];
		int connectionCnt = 0;
		// 빈 공간에 대해 상하좌우 연결요소 찾기
		for (d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(isInRange(nr, nc) && isConnected(nr, nc, d)) {
				isConnected[d] = true;
				connectionCnt++;
			}
		}
		// 만약 빈 공간의 다음이 종료 위치라면 cnt가 2보다 작게됨
		if(connectionCnt < 2) {
			for (d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(!isInRange(nr, nc)) continue;
				if(map[nr][nc] == 'M' || map[nr][nc] == 'Z') {
					isConnected[d] = true;
					continue;
				}
			}	
		}
		return isConnected;
	}
	
	private static boolean isConnected(int r, int c, int d) {
		if (d == 0) {
			if (map[r][c] == '|' || map[r][c] == '+' || map[r][c] == '1' || map[r][c] == '4') {
				return true;
			}
		} else if (d == 1) {
			if (map[r][c] == '-' || map[r][c] == '+' || map[r][c] == '3' || map[r][c] == '4') {
				return true;
			}
		} else if (d == 2) {
			if (map[r][c] == '|' || map[r][c] == '+' || map[r][c] == '2' || map[r][c] == '3') {
				return true;
			}
		} else if (d == 3) {
			if (map[r][c] == '-' || map[r][c] == '+' || map[r][c] == '1' || map[r][c] == '2') {
				return true;
			}
		}
		return false;
	}
	
	private static int getStartDirection() {
		int r = start.r, c = start.c; // 탐색 시작 위치

		// 처음 방향 설정
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (!isInRange(nr, nc)) continue; // 범위체크
			
			if(isConnected(nr, nc, d)) {
				return d;
			}
		}
		return -1;
	}
	
	private static void swapStartAndEnd() {
		Location temp = start;
		start = end;
		end =temp;
		map[start.r][start.c] = 'M';
		map[end.r][end.c] = 'Z';
	}
	
	private static boolean isInRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C ? true : false;
	}
}
