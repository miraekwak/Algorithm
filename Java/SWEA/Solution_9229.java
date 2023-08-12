package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N개의 과자 봉지중 2개의 과자봉지를 골라 M그램을 초과하지 않는 최대 무게
 * 방법이 없는 경우 -1
 * 
 * 조합
 * N개 중에 2개를 고르고 무게 확인
 * M그램과 비교
 * 
 * nCr = 1000! /  (998! * 2!) =>  1000 * 999 / 2!
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_9229 {

	private static int[] snack;
	private static int N, M, max_weight;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
					
			snack = new int[N];
			line = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				snack[i] = Integer.parseInt(line[i]);					
			}
			
			// 초기화
			max_weight = -1;
			
			selectSnack(0, 0, 0);
			
			sb.append("#"+t+" "+max_weight+"\n");
		}
		System.out.println(sb);
	}
	
	private static void selectSnack(int cnt, int str, int subSum) {
		if(cnt == 2) {
			if(subSum != 0 && subSum <= M) {
				max_weight = Math.max(max_weight, subSum);				
				return;
			}
		}
		
		for(int i=str; i<N; i++) {
			if(subSum+snack[i] > M) continue;
			selectSnack(cnt+1, i+1, subSum+snack[i]);
		}
	}
}

