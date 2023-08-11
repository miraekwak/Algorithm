package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 문제]
 * 왼쪽부터 1,2,3,4자리 모두 소수인 수
 * N자리 숫자 중에서 어떤 수들이 신기한 소수 인지 찾기
 * 
 * 입력]
 * 1<=N<=8
 * 
 * 풀이]
 * 중복 순열을 활용한 풀이
 * 첫번째 자리에서 부터 소수인지 확인하면 해당 번째에 존재하는 수들을 건너 뛸 수 있음
 * ex) N=4일 때, 1 소수 아님 => 1천대 수 건너뛰기
 * 
 * @author SSAFY
 *
 */
public class boj_2023_신기한소수_2 {
	private static int N;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		for(int i=2; i<=9; i++) {
			if(!isPrime(i)) continue;
			duplicatedPermu(1, i);			
		}

		System.out.println(sb);
	}
	
	private static void duplicatedPermu(int cnt, int num) {
		if(cnt == N) {
			sb.append(num+"\n");
			return;
		}

		for(int i=0; i<=9; i++) {
			int temp = num*10+i;
			if(!isPrime(temp)) continue;
			duplicatedPermu(cnt+1, temp);
		}
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
