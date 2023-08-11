package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15650_N과M2 {
	private static int N, M;
	private static int[] numbers;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		numbers = new int[M];
		sb = new StringBuilder();
		permu(0, 1);
		System.out.println(sb);
	}
	
	private static void permu(int cnt, int str) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=str; i<=N; i++) {
			numbers[cnt] = i;
			permu(cnt+1, i+1);
		}
	}
}
