package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 18장의 카드를 9개씩 나눔
 * 9라운드로 게임
 * 라운드 별로
 * 	- 한장씩 카드 내기
 * 	- 높은 수인 사람 = 두 카드에 적힌 합
 * 	- 낮은 수인 사람 = 점수 x
 * 총점이 높은 사람 승자
 * 총점이 같을 경우 무승부
 * 
 * 규영이가 받은 9장의 카드 수 주어짐
 * 규영이가 이기는 경우와 지는 경우 구하기
 * 
 * 카드 수는 1이상 18이하
 * 
 * 9! => 약 36만
 * 인영이 받은 카드 순열 구하기
 * 	- 점수 계산
 * 	- 카운트
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_6808 {

	private static int[] gue_card;
	private static int[] in_card;
	private static int win_cnt, lose_cnt;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 초기화
			gue_card = new int[9];
			in_card = new int[9];
			win_cnt=0;
			lose_cnt=0;
			
			int flag = 0;
			String[] line = br.readLine().split(" ");
			for(int i=0; i<9; i++) {
				gue_card[i] = Integer.parseInt(line[i]);
				flag = flag | (1<<gue_card[i]);
			}
			for(int i=1, idx=0; i<19; i++) {
				if((flag & 1<<i) == 0) {
					in_card[idx++] = i;
				}
			}
			
			putCard(0, 0, 0, 0);

			sb.append("#"+t+" "+win_cnt+" "+lose_cnt+"\n");
		}
		System.out.println(sb);
	}
	
	private static void putCard(int cnt, int flag, int gue, int in) {
		if(cnt == 9) {
			if(gue > in) win_cnt++;
			if(gue < in) lose_cnt++;
			return;
		}
		
		for(int i=0; i<9; i++) {
			if((flag & 1<<i) != 0) continue;
			if(gue_card[cnt] > in_card[i]) putCard(cnt+1, flag | 1<<i, gue+gue_card[cnt]+in_card[i], in); 
			else putCard(cnt+1, flag | 1<<i, gue, in+gue_card[cnt]+in_card[i]);
		}
	}

}

