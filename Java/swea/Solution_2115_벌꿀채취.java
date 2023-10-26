package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * NxN 벌통
 * 벌꿀을 채취해 최대 수익 얻기
 * 채취 가능 벌통 수 M
 * 	- 2명의 일꾼은 가로로 연속되도록 M개 벌통 채취
 * 	- 선택한 벌통은 겹치지 않음
 * 벌꿀 채취
 * 	- 최대 양은 C
 * 	- C를 넘지않도록 채취 가능
 * 벌꿀 판매
 * 	- 각 용기별 꿀의 양의 제곱수 만큼 이익
 * 
 * 출력]
 * 수익의 합이 최대가 되는 경우의 최대 수익
 *
 * 제한]
 * 3<=N<=10 
 * 1<=M<=5
 * M <= N
 * 10<=C<=30
 * 1<=꿀의양<=9
 *
 * 알고리즘]
 * 벌꿀 양 : 2차원 배열
 * 선택한 벌통 : 2차원 배열 -> 매 벌통 조합 시 초기화
 * 
 * 1. 2명의 일꾼이 M개의 벌통을 선택하는 경우의 수 -> 2차원 배열 2중 반복
 * 	- 벌꿀 선택 시 최대 양을 넘는지 계산
 * 	- 넘는 다면
 * 		M개에 대해 C를 넘지않는 최대 조합 계산
 * 	- 아니라면 채취
 * 2. 벌꿀 수익 계산
 * 	- 제곱수
 *
 * @author SSAFY
 *
 */
public class Solution_2115_벌꿀채취 {
	private static int N, M, C, suMmap[][], map[][];
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			suMmap = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());			
				for(int j=1; j<=N; j++) {
					int honey = Integer.parseInt(st.nextToken());
					suMmap[i][j] = honey + suMmap[i][j-1];
					map[i][j] = honey;
				}
			}

			sb.append("#"+t+" "+getMaxHoney()+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getMaxHoney() {
		int sum = 0;
		for(int ai=1; ai<=N; ai++) { // A 사람의 위치
			for(int aj=1; aj<=N-M+1; aj++) {
				for(int bi=ai; bi<=N; bi++) { // B사람의 위치
					for(int bj=1; bj<=N-M+1; bj++) {
						// 겹칠 경우 제외
						if(ai == bi && aj <= bj && bj < aj+M ) continue;
						if(ai == bi && aj <= bj+M && bj+M < aj+M ) continue;
						
						// 벌통 꿀양
						int asum = suMmap[ai][aj+M-1] - suMmap[ai][aj-1];
						int bsum = suMmap[bi][bj+M-1] - suMmap[bi][bj-1];
						
						// 적절하게 맞추고 제곱값 구하기
						if(asum > C) asum = calAppropriateHoney(ai, aj);
						else asum = calSquare(ai, aj);
						if(bsum > C) bsum = calAppropriateHoney(bi, bj);
						else bsum = calSquare(bi, bj);

						sum = Math.max(sum, asum + bsum);
					}
				}
			}
		}
		return sum;
	}
	
	private static int calAppropriateHoney(int r, int c) {
		int sum = 0;
		for(int flag=1; flag<(1<<M)-1; flag++) {
			int subsum = 0;
			int subSquare = 0;
			for(int f=0; f<M; f++) {
				if((flag & (1<<f)) != 0) {
					subsum += map[r][c+f];
					subSquare += map[r][c+f]*map[r][c+f];
				}
			}
			if(subsum <= C) sum = Math.max(sum, subSquare);
		}
		return sum;
	}
	
	private static int calSquare(int r, int c) {
		int sum = 0;
		for(int j=c; j<c+M; j++) {
			sum += map[r][j]*map[r][j];
		}
		return sum;
	}
}

