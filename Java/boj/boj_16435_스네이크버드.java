package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제]
 * 과일 하나를 먹으면 길이 1 증가
 * i(1<=i<=N)번째 과일의 높이는 hi
 * 스네이크버드는 자신의 길이보다 작거나 같은 높이 과일 먹음
 * 처음 길이 L일 때 과일들을 먹어 늘릴 수 있는 최대 길이
 * 
 * 입력]
 * 과일 개수 N (1<=N<=1000)
 * 스네이크 버드 길이 L (1<=L<=10000)
 * 정수 h1, h2, ..., hn(1<=hi<=10000)
 * 
 * 풀이]
 * 오름차순 정렬
 * 제일 낮은 높이의 과일부터 차례로 탐색
 * 	- 먹을 수 있다면 먹고 길이 증가
 * 	- 자신의 길이보다 높을 경우 멈춤
 * 
 * 시간복잡도 = NlogN + N = NlogN
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_16435_스네이크버드 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int L = Integer.parseInt(line[1]);
		
		int[] numbers = new int[N];
		line = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(line[i]);
		}
		
		Arrays.sort(numbers);
		
		for(int i=0; i<N; i++) {
			if(L < numbers[i]) break;
			L++;
		}
		System.out.println(L);
	}
}
