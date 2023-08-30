package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * NxN
 * 1x2(-), 2x1(|), 2x2(\) 방향 파이프
 * 파이프 밀기
 * 	- 오른쪽 아래
 * 	- 아래
 * 	- 오른쪽 아래 대각선
 * 회전 45도
 * 	- 가로 => 2가지
 *  - 세로 => 2가지
 *  - 대각선 => 3가지
 * 
 * 파이프 처음 위치 (1,1)-(1,2)
 * 빈칸 0, 벽 1
 * 
 * 알고리즘] DP
 * 내가 현재 r,c로 파이프를 놔야할 때 올 수 있는 방법은 5가지
 * 1. (r-1, c)위치에서 
 * 		파이프가 세로로 있을 때
 * 		- 세로로 민다
 * 		파이프가 대각선으로 있을 때
 * 		- 세로로 민다.
 * 2. (r-1, c-1)위치에서 
 * 		파이프가 대각선으로 있을 때
 * 		- 대각선으로 민다.
 * 		파이프가 가로로 있을 때
 * 		- 대각선으로 민다. 
 * 		파이프가 세로로 있을 때
 * 		- 대각선으로 민다.
 * 3. (r, c-1) 위치에서 
 * 		파이프가 가로로 있을 때
 * 		- 가로로 민다.
 * 		파이프가 대각선으로 있을 때
 * 		- 가로로 민다.
 * 
 * 3차원 배열을 통해 x, y, z 축이라 하면
 * x 축 : 열
 * y 축 : 행
 * z 축 : 현 위치의 파이프 형태
 * 
 * @author SSAFY
 *
 */
public class boj_17070_파이프옮기기1_DP {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 0 : 벽 또는 빈칸
		// 1 : 가로
		// 2 : 세로
		// 3 : 대각선
		int[][][] dp = new int[N+1][N+1][4];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				dp[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				// 벽이면 이동
				if(dp[i][j][0] == 1) continue;
				
				// 빈칸인 경우
				if(i==1) { // 첫번째 행의 방법은 가로로 미는 것 만 존재
					if(j==2) dp[i][j][1] = 1; // 시작위치 표시
					else dp[i][j][1] = dp[i][j-1][1];
					continue;
				}
				// 가로로 위치
				dp[i][j][1] = dp[i][j-1][1] + dp[i][j-1][3];
				
				// 세로로 위치
				dp[i][j][2] = dp[i-1][j][2] + dp[i-1][j][3];
			
				// 대각선 위치
				if(dp[i][j-1][0] == 0 && dp[i-1][j][0] == 0) {
					dp[i][j][3] = dp[i-1][j-1][1] + dp[i-1][j-1][2] + dp[i-1][j-1][3];
				}
			}
		}

		System.out.println(dp[N][N][1]+dp[N][N][2]+dp[N][N][3]);
	}
}
