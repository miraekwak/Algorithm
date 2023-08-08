package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * NxM 크기 배열에서 배열 회전 R번 적용
 * 2 ≤ N, M ≤ 300
 * 1 ≤ R ≤ 1,000
 * min(N, M) mod 2 = 0
 * 1 ≤ 원소 값 ≤ 108
 * 
 * 회전은 바깥부터 같은 범위에 있는 것 끼리 반시계방향 회전
 * 
 * 바깥줄부터 안쪽 줄로 이동하면서 한칸 씩 돌리기
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_16926 {
	
	private static int N, M, R;
	private static int[][] numbers;
	private static int[] dr = {0, 1, 0, -1}; // 우 -> 하 -> 좌 -> 상
	private static int[] dc = {1, 0, -1, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		
		numbers = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				numbers[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// R번의 회전 시작
		for(int r=0; r<R; r++) {
			rotate();			
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(numbers[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 각 방향 별 이동 횟수를 계산해 방향 별로 이동하는 방법
	 * 첫 번째 값을 저장해두고 반시계 방향대로 값 이동
	 */
	private static void rotate() {
		int circleNum = Math.min(N, M) /2; // 배열의 원 횟수 
		for(int i=0, space=1; i<circleNum; i++, space+=2) { 
			// space는 현재 자신의 위치를 제외하기 위한 초기값 1,
			// 안쪽 원의 경우 양옆 공백을 계산하기 위함
			int temp = numbers[i][i];
			int r = i;
			int c = i;
			for(int j=0; j<M-space; j++) {
				numbers[r][c] = numbers[r][c+1];
				c = c+1;
			}
			for(int j=0; j<N-space; j++) {
				numbers[r][c] = numbers[r+1][c];
				r = r+1;
			}
			for(int j=0; j<M-space; j++) {
				numbers[r][c] = numbers[r][c-1];
				c = c-1;
			}
			for(int j=0; j<N-space-1; j++) {
				numbers[r][c] = numbers[r-1][c];
				r = r-1;
			}
			numbers[r][c] = temp;
		}
	}
	
	/**
	 * 각 방향 별 이동 횟수를 설정하여 최종 위치(출발위치에서 행-1)까지 이동하는 방법
	 * 첫 번째 값을 저장해두고 반시계 방향대로 값 이동
	 */
	private static void rotate2() {
		int circleNum = Math.min(N, M) /2;
		int d = -1;
		for(int i=0, space=1; i<circleNum; i++, space+=2) {
			int first = numbers[i][i];
			int r=i, c=i;
			int goCnt = 0;
			while(r!=i+1 || c!=i) {
				if(goCnt == 0) {
					d = (d+1)%4;
					goCnt = d%2==0 ? M-space : N-space;
					continue;
				}
				numbers[r][c] = numbers[r+dr[d]][c+dc[d]];
				r = r+dr[d];
				c = c+dc[d];
				goCnt--;
			}
			numbers[r][c] = first;
		}
	}
}
