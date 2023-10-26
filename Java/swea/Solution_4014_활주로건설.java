package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간]
 * 시작 : 2:00
 * 종료 : 3:00
 * 
 * 문제]
 * NxN
 * 각 셀은 지형의 높이
 * 가로 또는 세로방향 활주로 건설
 * 높이가 동일한 구간에 건설 가능
 * 높이가 다른 구간은 경사로 설치 후 활주로 건설
 * - 경사로는 길이가 x, 높이가 1
 * - 낮은 지형의 높이가 동일하게 경사로 길이만큼 연속되어야함
 * 
 * 입력]
 * 6 <= N <= 20
 * 경사로 높이 1, 2 <= X <=4
 * 1 <= 지형의 높이 <= 6
 * 
 * 알고리즘]
 * 가로탐색 세로탐색
 * 높이가 이어지는 구간 계산
 * 높이가 1이상 차이나면 활주로 건설 실패
 * 높이가 1차이나면 경사로 놓을 수 있는지 확인
 * - 경사가 높아지면 이전에 이어지던 구간의 길이 >= x
 * - 경사가 낮아지면 앞의 x개 확인
 * 
 * 시간복잡도]
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_4014_활주로건설 {

	static int N, X, map[][], CNT;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			CNT = 0;
			for(int i=0; i<N; i++) {
				if(checkRow(i)) CNT++;
				if(checkCol(i)) CNT++;
			}
			sb.append("#"+tc+" "+CNT+"\n");
		}
		System.out.println(sb);
	}
	private static boolean checkCol(int c) {
		int cnt = 1;
		int i=1;
		int prev, curr;
		while(i<N) {
			prev = map[i-1][c];
			curr = map[i][c];
			if(prev == curr) {
				cnt++;
				i++;
				continue;
			}
			if(Math.abs(prev - curr) > 1) {
				return false;
			}
			// 1차이 나는 경우
			if(prev > curr) { // 하강
				int x = 0;
				while(i < N && curr == map[i][c]) {
					i++; 
					x++;
				}
				if(x < X) return false;
				cnt = x-X;
			} else { // 상승
				if(cnt < X) return false;
				cnt = 1;
				i++;
			}
		}
		return true;
	}
	private static boolean checkRow(int r) {
		int cnt = 1;
		int i=1;
		int prev, curr;
		while(i < N) {
			prev = map[r][i-1];
			curr = map[r][i];
			if(prev == curr) {
				cnt++;
				i++;
				continue;
			}
			if(Math.abs(prev - curr) > 1) return false;
			
			// 1차이 나는 경우
			if(prev > curr) { // 하강
				int x = 0;
				while(i < N && curr == map[r][i]) {
					i++; 
					x++;
				}
				if(x < X) return false;
				cnt = x-X;
			} else { // 상승
				if(cnt < X) return false;
				cnt = 1;
				i++;
			}
		}
		return true;
	}
}
