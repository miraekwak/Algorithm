package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * NxM 크기 배열에서 배열 연산 R번 적용
 * 2 ≤ N, M ≤ 100
 * 1 ≤ R ≤ 1,000
 * N, M은 짝수
 * 1 ≤ 원소값 ≤ 108
 * 
 * 배열 연산 종류> 6가지 
 * 1. 상하반전
 * 2. 좌우 반전
 * 3. 오른쪽 90도 회전
 * 4. 왼쪽 90도 회전
 * 5. 4개의 부분 배열을 시계방향 회전
 * 6. 4개의 부분 배열을 반시계방향 회전
 * 
 * 연산 한번 : 100 x 100
 * R번 연산 : 100 x 100 x 1000
 * 
 * @author SSAFY
 *
 */
public class boj_16935_배열돌리기3 {
	
	private static int N, M, R;
	private static int[][] numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		
		numbers = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				numbers[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		// R번의 연산 시작
		line = br.readLine().split(" ");
		for(int r=0; r<R; r++) {
			int number = Integer.parseInt(line[r]);
			switch(number) {
			case 1:
				method1();
				break;
			case 2:
				method2();
				break;
			case 3:
				method3();
				break;
			case 4:
				method4();
				break;
			case 5:
				method5();
				break;
			case 6:
				method6();
				break;
			}
			
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(numbers[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * 배열 상하 반전
	 * -> 전체 행의 절반만 실행하며 반전되는 행끼리의 참조값 변경
	 */
	private static void method1() {
		for(int i=0; i<N/2; i++) {
			int[] temp = numbers[i];
			numbers[i] = numbers[N-1-i];
			numbers[N-1-i] = temp;
		}
	}
	
	/**
	 * 배열 좌우 반전
	 * -> 전체 열의 절반 만 실행하며, 반전되는 열끼리의 값 변경
	 */
	private static void method2() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				int temp = numbers[i][j];
				numbers[i][j] = numbers[i][M-1-j];
				numbers[i][M-1-j] = temp;
			}
		}
	}
	
	/**
	 * 오른쪽으로 90도 회전 함수 
	 */
	private static void method3() {
		int[][] original = numbers; // 기존 배열 값 저장
		numbers = new int[M][N]; // 변환된 행열 크기로 새 배열 생성
		
		for(int i=0; i<M; i++) {
			for(int j=0, r=N-1; j<N; j++, r--) {
				// 왼쪽 아래 열(왼->오) -> 행(아래->위) 탐색
				numbers[i][j] = original[r][i] ; 
			}
		}
		// 행열 크기 변경
		int temp = N;
		N = M;
		M = temp;
	}
	
	/**
	 * 왼쪽으로 90도 회전 함수
	 */
	private static void method4() {
		int[][] original = numbers; // 기존 배열 값 저장
		numbers = new int[M][N]; // 변환된 행열 크기로 새 배열 생성
		
		for(int i=0, c=M-1; i<M; i++, c--) {
			for(int j=0; j<N; j++) {
				// 왼쪽 아래 열(왼->오) -> 행(위->아래) 탐색
				numbers[i][j] = original[j][c] ;
			}
		}
		// 행열 크기 변경
		int temp = N;
		N = M;
		M = temp;
	}
	
	/**
	 * 4개의 좌표 값을 시계방향으로 이동
	 * @param r1 : 첫번째 행 좌표
	 * @param c1 : 첫번째 열 좌표
	 * @param r2 : 두번째 행 좌표
	 * @param c2 : 두번째 열 좌표
	 * @param r3 : 세번째 행 좌표
	 * @param c3 : 세번째 열 좌표
	 * @param r4 : 네번째 행 좌표
	 * @param c4 : 네번째 열 좌표
	 */
	private static void swap(int r1, int c1, int r2, int c2, int r3, int c3, int r4, int c4) {
		int temp = numbers[r4][c4];
		numbers[r4][c4] = numbers[r3][c3];
		numbers[r3][c3] = numbers[r2][c2];
		numbers[r2][c2] = numbers[r1][c1];
		numbers[r1][c1] = temp;
	}
	
	/**
	 * 4개의 부분 배열을 시계방향으로 이동
	 */
	private static void method5() {
		int r_middle = N/2;
		int c_middle = M/2;
		
		for(int i=0; i<r_middle; i++) {
			for(int j=0; j<c_middle; j++) {
				// 좌상, 우상, 우하, 좌하 순서로 시계방향 변경
				swap(i, j, i, j+c_middle, i+r_middle, j+c_middle, i+r_middle, j);
			}
		}
	}
	
	/**
	 * 4개의 부분 배열을 반시계 방향으로 이동 
	 */
	private static void method6() {
		int r_middle = N/2;
		int c_middle = M/2;
		
		for(int i=0; i<r_middle; i++) {
			for(int j=0; j<c_middle; j++) {
				// 좌하, 우하, 우상, 좌상 순서로 시계방향 변경
				// 해당 함수는 시계방향으로 값을 변경하므로 거꾸로 좌표를 적어야함
				swap(i+r_middle, j, i+r_middle, j+c_middle, i, j+c_middle, i, j);
			}
		}
	}
}
