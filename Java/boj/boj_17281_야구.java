package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제]
 * 9명으로 이루어진 두팀 번갈아 게임
 * N이닝 - 각 이닝은 공격과 수비
 * 한 이닝에서 3아웃 발생 시 이닝 종료 후 공격 수비 체인지
 * 경기 시작전 타순 선정 (9번타자 -> 1번타자 이어서)
 * 이닝의 마지막 타자, 다음이닝은 마지막 타자 다음부터
 * 선수가 1,23루 거쳐 홈도달시 1점 획득
 * 
 * 안타  : 타자와 주자 한루씩 진루
 * 2루타 : 타자와 주자 두루씩 진루
 * 3루타 : 타자와 주자 세루씩 진루
 * 홈런 : 타자와 주자 홈까지 진루
 * 아웃 : 모든 주자 진루 못함, 공격 팀 아웃 1증가
 * 
 * 1번부터 9번 타자가 있을 때 가장 많이 득점하는 타순 찾기
 * 1번 선수는 4번 타자
 * 
 * 알고리즘]
 * 2차원 배열로 이닝 별 선수의 득점 저장
 * 1. 1번 번수 제외 8명에 대해 순열
 * 2. 순열대로 게임 진행
 * 3. 득점 계산
 * 1-3을 모든 순열에 대해 반복 후 계산
 * 
 * 시간 복잡도]
 * 순열 : 8!
 * 
 * @author SSAFY
 *
 */
public class boj_17281_야구 {

	private static int N, inings[][], selected[];
	private static boolean[] visited;
	
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		StringTokenizer st;
		inings = new int[N][N];
		visited = new boolean[N];
		selected = new int[9];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				inings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

	private static void permu(int cnt) {
		if(cnt == N) {
			
		}
		
		if(cnt == 4) {
			selected[cnt] = 0;
			permu(cnt+1);
			return;
		}
		
		for(int i=1; i<N; i++) {
			if(visited[i]) continue;
			selected[cnt] = i;
			permu(cnt+1);
		}
	}
	
	private static int baseball() {
		int score = 0;
		int ining = 0;
		
		int[] base = new int[4];
		while(ining++ < N) {
			
		}
		
		return score;
	}
}
