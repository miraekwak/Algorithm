package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * 빵집 R*C
 * 첫번째 열 = 빵집 가스관
 * 마지막 열 = 원웅이 빵집
 * 가스관과 빵집을 파이프로 연결
 * 빵집과 가스관 사이 건물 존재 가능(건물이 있는 경우 파이프 X)
 * 첫번째 열 시작 마지막 열 종료
 * 방향 : 오른쪽, 오른쪽 위, 오른쪽 아래
 * 가스관과 빵집을 연결하는 파이프라인 여러개 설치
 * 단, 경로는 겹칠 수 없고, 각 칸엔 하나의 파이프만 가능
 * 
 * 출력]
 * 파이프 라인 최대 개수
 * 
 * 풀이]
 * 2차원 방문 배열
 * 
 * 첫번째 열의 각 출발지에 대해 dfs
 * dfs()를 통해 첫번째 열에서 도착지 까지 판단
 * 	- 도착하거나 도착하지 못할 경우 
 * 	- 다음 열에 대한 dfs 시작
 * 	=> 재귀 깊이가 너무 깊어질듯,,?
 * 
 * 500 * 10000 => 5,000,000 *3 => 15,000,000
 * 
 * 그리디
 * boolean 배열로 방문 여부 표시 true: 방문 불가, false : 방문 가능
 * 각 행에서 열까지 도달할 수 있는지 확인 후 갈 수 있다면 개수 증가
 * 
 * 우상, 우, 우하 순으로 확인
 * 	- 다음과 같이 확인해야 밑의 행에서 갈 수 있는 경우의 수가 많아짐
 * 방문 배열 체크
 * 	- 이미 방문한 곳은 true
 * 	- 이때 경로를 찾지 못해 돌아오는 경우 false를 해줄 필요 없음
 * 		why? 위의 행에서 가서 길을 찾지 못했다면 밑의 행에서도 찾을 수 없음!
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_3109_빵집 {
	
	private static int R, C, PIPE_NUM;
	private static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		visited = new boolean[R][C];
		for(int i=0; i<R; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(line[j] == 'x') visited[i][j] = true;
			}
		}
		
		for(int i=0; i<R; i++) {
			dfs(i, 0);
		}
						
		System.out.println(PIPE_NUM);
	}
	
	private static boolean dfs(int r, int c) {
		visited[r][c] = true;		
		if(c == C-1) { // 마지막 열에 도착함
			PIPE_NUM++;
			return true;
		}
		
		if(r-1 >= 0 && c+1 < C && !visited[r-1][c+1]) {
			if(dfs(r-1, c+1)) return true;
		}
		
		if(c+1 < C && !visited[r][c+1]) {
			if(dfs(r, c+1)) return true;
		}
		
		if(r+1 < R && c+1 < C && !visited[r+1][c+1]) {
			if(dfs(r+1, c+1)) return true;
		}

		return false;
	}	
}
