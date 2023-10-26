package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 수 N개 주어질 때 i번째~j번째 수 합 구하기
 * 1<=N<=1024
 * 1<=M<=100_000
 * 
 * N개의 수에 대해 누적합 구하기
 * 누적합 구하는 공식
 * (x, y) => 0,0부터  x행, y열까지 직사각형의 합
 * arr[N+1][N+1]
 * (x1, y1), (x2, y2)
 * arr[x2][y2] - arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1]
 * 
 * 시간복잡도>
 * 1. 누적합 방식
 * 누적합 구하기
 * for 1~1024
 * 	for 1~1024
 * M개의 구간합 구하기
 * for 1~100_000
 * 
 * 
 * 2. 완전 탐색
 * M개의 구간합
 * for 1 ~ 100_000
 *  for 1~1024
 * 	 for 1~1024
 * 
 * @author SSAFY
 *
 */
public class boj_11660_구간합구하기5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		int[][] numbers = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			line = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				numbers[i][j] = Integer.parseInt(line[j-1]) + numbers[i-1][j] + numbers[i][j-1] - numbers[i-1][j-1];
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int t=0; t<M; t++) {
			line = br.readLine().split(" ");
			int x1 = Integer.parseInt(line[0]);
			int y1 = Integer.parseInt(line[1]);
			int x2 = Integer.parseInt(line[2]);
			int y2 = Integer.parseInt(line[3]);
			
			sb.append(numbers[x2][y2] - numbers[x1-1][y2] - numbers[x2][y1-1] + numbers[x1-1][y1-1]).append("\n");
		}
		System.out.println(sb);
	}
}
