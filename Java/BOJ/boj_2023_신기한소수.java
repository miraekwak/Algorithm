package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 문제]
 * 왼쪽부터 1,2,3,4자리 모두 소수인 수
 * N자리 숫자 중에서 어떤 수들이 신기한 소수 인지 찾기
 * 
 * 입력]
 * 1<=N<=8
 * 
 * 풀이]
 * 1<<N 크기의 배열
 * 2부터 해당 수들에 대해 소수 찾기 -> true or false
 * N자리 수들에 대해 각 자리수에 대해 배열 확인 
 * 
 * @author SSAFY
 *
 */
public class boj_2023_신기한소수 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int maximum = (int) Math.pow(10, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=(int)Math.pow(10, N-1); i<maximum; i++) {
			boolean ok = true;
			for(int zeroCnt = N-1; zeroCnt > 0; zeroCnt--) {
				if(!isPrime(i / (int)Math.pow(10, zeroCnt))) {
					ok = false;
					break;
				}
			}
			if(ok && isPrime(i)) sb.append(i+"\n");
		}
		System.out.println(sb); //400만
	}
	
	private static boolean isPrime(int n) {
		if(n<=1) return false;
		
		if(n%2 == 0) {
			return n==2 ? true : false;
		}
		
		for(int i=3; i<=Math.sqrt(n); i+=2) {
			if(n%i == 0) return false;
		}
		
		return true;
	}
}
