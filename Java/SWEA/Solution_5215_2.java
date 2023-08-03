package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5215_2 {

	private static int[] calorie;
	private static int[] taste;
	private static int N, L, total;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			L = Integer.parseInt(line[1]);
			
			// 필요한 배열 및 변수 초기화
			calorie = new int[N];
			taste = new int[N];
			total = 0;
			
			// 맛과 칼로리 입력
			for(int i=0; i<N; i++) {
				line = br.readLine().split(" ");
				taste[i] = Integer.parseInt(line[0]);
				calorie[i] = Integer.parseInt(line[1]);
			}
			
			// 최대 맛 구하기
			search(0, 0, 0, 0); // 재귀
//			search(); // 비트마스크
			
			sb.append("#"+t+" "+total+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 재귀를 활용한 최대 맛 구하기
	 * @param cnt : 살펴본 재료 개수
	 * @param selectedCnt : 선택한 재료 개수
	 * @param cal : 현재까지 선택한 재료의 칼로리 합 
	 * @param tast : 현재까지 선택한 재료의 맛 합
	 */
	private static void search(int cnt, int selectedCnt, int cal, int tast) {
		if(cal > L) return;
		if(cnt == N) {
			if(selectedCnt > 0) {
				total = Math.max(total, tast);				
			}
			return;
		}
		search(cnt+1, selectedCnt+1, cal+calorie[cnt], tast+taste[cnt]);
		search(cnt+1, selectedCnt, cal, tast);
	}
	
	/**
	 * 비트마스킹을 활용한 최대 맛 구하기
	 */
	private static void search() {
		// 부분 조합 생성
		for(int flag=1; flag<=Math.pow(2, N)-1; flag++) {
			int tast =0, cal = 0;
			boolean isTaste = true;
			// 하나의 조합에 대해 재료별로 살피기
			for(int bit=0; bit<N; bit++) {
				// 선택된 재료라면 더하기
				if((flag & 1<<bit)>0) {
					tast += taste[bit];
					cal += calorie[bit];
				}
				if(cal > L) {
					isTaste = false;
					break;
				}
			}
			// 칼로리가 넘지 않는다면 맛 최대값 갱신
			if(isTaste) {
				total = Math.max(total, tast);
			}
		}
	}
}
