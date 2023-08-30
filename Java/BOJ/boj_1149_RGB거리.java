package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * 빨강, 초록, 파랑
 * 1번 집부터 N번집
 * 각 집은 좌, 우에 있는 집과 색이 달라야한다.
 * 
 * 각 색으로 칠하는 비용이 있을 때 최소 비용 구하기
 * 
 * 알고리즘]
 * 1. DP
 * R, G, B 각각에 대해 최소 값을 저장하기
 * N x 3 배열에서 각 열은 R, G, B를 나타냄
 * ex)
 * arr[2][0] : 2번째 집에서 R을 칠할 때 최소 비용
 * R을 칠할 수 있으려면 이전 집이 G or B
 * 이전 집 G의 최소 : arr[1][1]
 * 이전 집 B의 최소 : arr[1][2]
 * 이 둘중 최소 저장
 * 
 *
 */
public class boj_1149_RGB거리 {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][3];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + Integer.parseInt(st.nextToken());
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + Integer.parseInt(st.nextToken());
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + Integer.parseInt(st.nextToken());
		}
		int result = Math.min(dp[N][0], dp[N][1]);
		result = Math.min(dp[N][2], result);
		System.out.println(result);
	}
}
