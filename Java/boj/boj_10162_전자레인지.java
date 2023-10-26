package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A B C
 * 5 1 10
 * 
 * @author SSAFY
 *
 */
public class boj_10162_전자레인지 {

	public static void main(String[] args) throws Exception, IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		// 최소 단위가 10초 이므로 10으로 나눠지지 않는다면 T초를 맞출 수 없음
		if(T %10 != 0) {
			System.out.println(-1);
			return;
		}
		
		// 각 버튼에 대한 count 변수
		int A=0, B=0, C=0;
		
		// 시간이 긴 A버튼 부터 C버튼까지 차례로 진행
		A = T/(60*5); // 현재 시간이 5분보다 길다면 버튼 횟수 구하기
		T = T%(60*5); // 남은 시간 계산
		B = T/60; // 현재 시간이 1분보다 길다면 버튼 횟수 구하기
		T = T%60; // 남은 시간 계산
		C = T/10; // 현재 시간이 10초보다 길다면 버튼 횟수 구하기
		T = T%10; // 남은 시간 계산
		System.out.println(A + " "+B+" "+C); // 구한 횟수 출력	
	}

}
