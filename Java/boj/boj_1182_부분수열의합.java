package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1182_부분수열의합 {
	private static int N, S, CNT=0;
	private static int[] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		S = Integer.parseInt(line[1]);
		
		numbers = new int[N];
		line = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(line[i]);
		}
		
		choose(0, 0, 0);
		System.out.println(CNT);
	}

	/**
	 * 부분집합 생성 및 합 구하는 함수
	 * @param cnt : 현재까지 살펴본 원소 개수
	 * @param selectedCnt : 현재 선택된 원소 개수
	 * @param sum : 현재까지 선택된 원소들의 합
	 */
	private static void choose(int cnt, int selectedCnt, int sum) {
		if(cnt == N) {
			if(selectedCnt == 0) return;
			if(S == sum) {
				CNT++;
			}
			return;
		}

		choose(cnt+1, selectedCnt+1, sum+numbers[cnt]);
		choose(cnt+1, selectedCnt, sum);
	}
}
