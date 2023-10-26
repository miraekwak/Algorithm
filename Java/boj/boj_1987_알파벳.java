package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * R x C 보드
 * 보드 각 칸에 대문자 알파벳
 * 1,1에 말
 * 
 * 말은 상하좌우 중 한칸 이동
 * 새로 이동한 칸의 알파벳은 처음 나온 알파벳
 * 
 * 말이 최대 몇칸 지날 수 있는지 (시작 칸 포함)
 * 
 * DFS + 백트래킹
 * 4가지 칸으로 이동하는 경우
 * 26가지 알파벳에 대해 (r,c) 배열
 * 탐색할 경우 true 표시
 * 만약 true인 곳을 가려고 한다면 멈추고 최대 칸 갱신
 * 
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_1987_알파벳 {
	
	private static int R, C, CNT;
	private static boolean[] alpha;
	private static char[][] map;
	private static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	private static int[] dc = {0, 0, 1, -1}; // 상 하 좌 우
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray(); 
		}
		
		alpha = new boolean[26];
		alpha[(int)map[0][0] - (int)'A'] = true;
		findPath(0, 0, 1);
		System.out.println(CNT);
	}
	
	private static void findPath(int r, int c, int num) {
		for(int d=0; d<4; d++) {
			int next_r = r+dr[d];
			int next_c = c+dc[d];
			if(next_r >= 0 && next_r <R && next_c >= 0 && next_c < C) {
				int idx = (int)map[next_r][next_c] - (int)'A'; 				
				if(!alpha[idx]) {
					alpha[idx] = true;
					findPath(next_r, next_c, num+1);
					alpha[idx] = false;
					continue;
				}
			}
			CNT = Math.max(CNT, num);
		}
	}
}
