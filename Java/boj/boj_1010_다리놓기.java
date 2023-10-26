package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N - M
 * N개만큼 다리를 지을 때 경우의 수
 * 
 * 
 * 알고리즘]
 * 1. 조합
 * - M개 중에 N개를 선택하는 경우의 수
 * - MCN
 * - 30C15 -> 1억 5천 
 * - 시간 초과
 * 
 * 2. DP
 * - 파스칼의 삼각형
 * - 1C1 부터 시작해 차례로 구해가면서
 * - mCn 구하기
 * = mCn = m-1Cn + m-1Cn-1
 * 
 * @author SSAFY
 *
 */
public class boj_1010_다리놓기 {

	public static void main(String[] args) throws Exception, IOException {
		int[][] dp = new int[30][30];
		for(int i=0; i<30; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0 || i==j) dp[i][j]=1;
				else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=1; i<=T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			sb.append(dp[M][N]).append("\n");
		}
		System.out.println(sb);
	}
}
