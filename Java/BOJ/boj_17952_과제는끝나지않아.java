package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *
 */
public class boj_17952_과제는끝나지않아 {

	public static void main(String[] args) throws Exception, IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] works = new int[N]; // 해당 작업에 걸리는 시간
		int[] price = new int[N]; // 해당 작업을 완료했을 경우 부여되는 성과
		
		StringTokenizer st; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			// 작업 받기
			int n = Integer.parseInt(st.nextToken());
			if(n != 0) { // 작업이 0이 아니면 새로운 작업을 받고 저장
				price[i] = Integer.parseInt(st.nextToken());
				works[i] = Integer.parseInt(st.nextToken());
			}
			for(int j=i; j>=0; j--) { // 현재 작업부터 이전 작업을 차례로 탐색
				if(works[j] > 0) { // 만약 작업이 남았다면 
					works[j]--; // 작업에 1분 소요
					break; // 종료
				}
			}
		}
		int score = 0; // 최종 점수 계산
		for(int i=N-1; i>=0; i--) { // 최근 종료한 작업부터 탐색
			if(works[i] > 0) continue; // 작업이 남았다면 종료
			score += price[i]; // 작업이 완료됐다면 점수 계산
		}
		System.out.println(score);
	}
}
