package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * NxN 배열
 * 1<=원소 값<=N^2
 * 배열 내 원소는 서로 다름
 * 상하좌우 다른 방으로 이동 가능
 * 이동하려는 방이 존재 한다면
 * 		- 이동하려는 방에 적힌 숫자 = 현재 방에 적힌 숫자 + 1
 * 
 * 처음 어떤 수가 적힌 방에 있어야 가장 많은 개수 방을 이동할 수 있는지
 * => 출발해야하는 방의 숫자, 이동할 수 있는 방의 수 
 * (이동할 수 있는 방의 수가 같은 방이 여러개인 경우 가장 작은 것 출력)
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_1861 {

	private static int[] dr = {-1, 0, 1, 0}; // 상 우 하 좌
	private static int[] dc = {0, 1, 0, -1}; // 상 우 하 좌
	private static int[][] rooms;
	private static boolean[][] visited;
	private static int N, cnt, startRoomNum;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			// 초기화
			rooms = new int[N][N];
			visited = new boolean[N][N];
			cnt = 1;
			startRoomNum = 1;
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					rooms[i][j] = Integer.parseInt(line[j]);					
				}
			}
			
			// 최대 경로 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue; // 방문한 경로의 경우 이미 전에 최대 경로가 구해짐
					int path = getPathCount(i, j); // 최대 경로 구하기
					if(path > cnt) {
						cnt = path;
						startRoomNum = rooms[i][j];
					}
					else if(path == cnt) {
						startRoomNum = Math.min(startRoomNum, rooms[i][j]);
					}
				}
			}
			sb.append("# "+t+" "+startRoomNum+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getPathCount(int r, int c) {
		int path = 1;
		while(true) {
			boolean isRoomExist = false;
			for(int d=0; d<4; d++) {
				// 범위 예외처리
				if(r+dr[d] < 0 || r+dr[d] >= N || c+dc[d] < 0 || c+dc[d] >= N) continue;
				// 이동할 방이 1만큼 크지 않은 경우 예외처리
				if(rooms[r][c]+1 != rooms[r+dr[d]][c+dc[d]]) continue;
				// 이동할 수 있다면 이동하기
				r = r+dr[d];
				c = c+dc[d];
				path++;
				visited[r][c] = true;
				isRoomExist = true;
				break;
			}
			if(!isRoomExist) break;
		}
		return path;
	}
}

