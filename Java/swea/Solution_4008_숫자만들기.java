package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 시간]
 * 시작 : 1:00
 * 종료 : 1:30
 * 
 * 문제]
 * N개의 숫자에 +, -, x, /의 연산자 넣어 결과 구하기
 * 연산자의 우선순위는 고려하지 않고 차례대로 계산
 * 결과 최대 최소 값을 찾고 두 값의 차이 출력
 * 
 * 입력]
 * T 테스트 케이스 개수
 * N 숫자의 개수
 * +, -, *, / 연산자의 개수
 * N개의 숫자
 * 
 * 
 * 알고리즘] 
 * 순열
 * N-1개의 연산자를 나열하는 순열 구하기 
 * - 백트래킹 요소
 * - 중복된 순열 건너뛰기
 * 해당 순열에 대해 결과값 계산해 최대, 최소값 갱신
 * 
 *  
 * 시간복잡도]
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_4008_숫자만들기 {

	static int N, max, min, numbers[];
	static char[] operators, selected, op = {'+', '-', '*', '/'};
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=TC; tc++) {
			N = Integer.parseInt(br.readLine());
			operators = new char[N-1];
			st = new StringTokenizer(br.readLine());
			for(int i=0, idx=0; i<4; i++) {
				int cnt = Integer.parseInt(st.nextToken());
				while(cnt-- > 0) {
					operators[idx] = op[i];
					idx++;
				}
			}
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());				
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			selected = new char[N-1];
			visited = new boolean[N-1];
			permu(0);
			sb.append("#"+tc+" "+(max-min)+"\n");
		}
		System.out.println(sb);
	}
	
	private static void permu(int cnt) {
		if(cnt == N-1) {
			int total = numbers[0];
			for(int i=1; i<N; i++) {
				switch(selected[i-1]) {
				case '+':
					total += numbers[i];
					break;
				case '-':
					total -= numbers[i];
					break;
				case '*':
					total *= numbers[i];
					break;
				case '/':
					if(numbers[i] == 0) return;
					total /= numbers[i];
					break;
				}
			}
			// 갱신 
			min = Math.min(min, total);
			max = Math.max(max, total);			
			return;
		}
		
		char before = '.';
		for(int i=0; i<N-1; i++) {
			if(visited[i] || before == operators[i]) continue;
			visited[i] = true;
			selected[cnt] = operators[i];
			before = operators[i];
			permu(cnt+1);
			visited[i] = false;
		}
	}
}
