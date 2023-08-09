package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * {‘A’, ‘C’, ‘G’, ‘T’}의 문자열의 부분 문자열
 * 각 알파벳 별 최소 포함 개수 정해짐
 * DNA 문자열의 길이 |S| 
 * 부분 문자열의 길이 |P| 
 * 1 ≤ |P| ≤ |S| ≤ 1,000,000
 * 
 * 
 * 만들 수 있는 비밀 번호 종류의 수 구하기
 * 
 * 
 * 1. 탐색
 * 문자열의 첫번째 부터 P만큼 끊어서 살피고
 * 	최소 개수를 만족한 다면 count++;
 * for i 1~N
 * 	for j i~P
 * => 시간 초과
 * 
 * 2. 누적 개수
 * 2차원 배열[4][N]을 통해 문자 별 N번째 까지의 누적 개수 구하기
 * for i 1~N
 * 	for j i~P
 * 		누적개수를 통해 해당하는 범위의 개수를 살펴봄
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_12891 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		char[] dna = br.readLine().toCharArray();
		
		int[] key = new int[4]; // {‘A’, ‘C’, ‘G’, ‘T’}
		line = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			key[i] = Integer.parseInt(line[i]);
		}

		int cnt = 0; // 비밀번호 종류의 수
		int[][] count = new int[4][N+1]; // 4가지 문자에 대한 누적 개수
		char[] alpha = new char[] {'A', 'C', 'G', 'T'};
		// 누적 개수 구하기
		for(int i=0; i<4; i++) {
			for(int j=1; j<=N; j++) {
				if(dna[j-1] == alpha[i]) count[i][j] = count[i][j-1]+1;
				else count[i][j] = count[i][j-1];
			}
		}		
		
		// 가능한 부분 문자열에 대해 정해진 개수 이상인지 판단
		for(int i=0; i<=N-M; i++) {
			boolean isPossible = true;
			for(int j=0; j<4; j++) {
				if(count[j][i+M] - count[j][i] < key[j]) {
					isPossible = false;
					break;
				}
			}
			if(isPossible) cnt++;
		}
		System.out.println(cnt);
	}
}
