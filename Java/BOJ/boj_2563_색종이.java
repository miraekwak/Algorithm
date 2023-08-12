package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 가로, 세로 100인 정사각형 도화지에
 * 가로, 세로 10인 정사각형 색종이 여러개 올림
 * 색종이의 왼쪽 아래 위치가 주어질 때 색종이 영역의 넓이
 * 
 * 완전 탐색>
 * 도화지 크기만큼 2차원 boolean 배열 생성
 * 색종이 위치에 true
 * 완료 후 true 값이 존재하는 곳의 갯수 세기
 * 
 * for 1 ~ 100 : 색종이 수
 * 		for 1 ~10 : 색종이 가로
 * 			for 1~10 : 색종이 세로
 * 
 * 100 * 10 * 10 => 10000
 * 
 * @author SSAFY
 *
 */
public class boj_2563_색종이 {
	
	private static int N;
	private static boolean[][] paper;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		paper = new boolean[100][100];
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			coloring(x, y);
		}
		
		int black = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(paper[i][j]) black++;
			}
		}
		
		System.out.println(black);
	}
	
	private static void coloring(int x, int y) {
		for(int i=100-y-1; i>100-1-10-y; i--) {
			for(int j=x; j<x+10; j++) {
				paper[i][j] = true;
			}
		}
	}
}
