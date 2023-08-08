package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 수 N개 주어질 때 i번째~j번째 수 합 구하기
 * 1<=N<=100_000
 * 1<=M<=100_000
 * 
 * N개의 수에 대해 누적합 구하기
 * arr[N+1]
 * i번째~j번째 : arr[j] - arr[i]
 * 
 * 시간복잡도>
 * 1. 누적합 방식
 * 누적합 구하기
 * for 1~100_000
 * M개의 구간합 구하기
 * for 1~100_000
 * 
 * O(2N) -> O(N)
 * 
 * 2. 완전 탐색
 * M개의 구간합
 * for 1~100_000
 * 	for i~j (1~100_000)
 * 
 * O(N^2) => 10_000_000_000 백억
 * 자바 1초 1억번 연산
 * 
 * @author SSAFY
 *
 */
public class boj_11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		line = br.readLine().split(" ");
		int[] numbers = new int[N+1];
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(line[i-1]) + numbers[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<M; t++) {
			line = br.readLine().split(" ");
			int i = Integer.parseInt(line[0]);
			int j = Integer.parseInt(line[1]);
			sb.append(numbers[j]-numbers[i-1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
