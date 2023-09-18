package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제]
 * N (1 ≤ N ≤ 20)
 * 최대 5번 이동
 * 가장 큰 블록의 값 구하기
 * 
 * 알고리즘] - 완전 탐색
 * 4 * 4 * 4 * 4 * 4 => 1024
 * N x N 블록 생성
 * 초기상태에서 4가지 방향으로 이동시켜보기
 * 
 * 각 방향 별로 당기기 N^2
 * 1. 왼쪽
 * - 1열 부터 시작
 * - 0인 구간은 건너뛰기
 * - 값이 있을 때 오른쪽에 값이 있다면
 * 		- 합칠 수 있다면 합치고 오른쪽 0 세팅
 * 		- 못합친다면 이동
 * 2. 오른쪽
 * - N열부터 시작
 * - 1열 부터 시작
 * - 0인 구간은 건너뛰기
 * - 값이 있을 때 왼쪽에 값이 있다면
 * 		- 합칠 수 있다면 합치고 왼쪽 0 세팅
 * 		- 못합친다면 이동
 * 
 * 3. 위
 * 
 * 4. 아래
 * 
 * 빈공간 채우기위해 마지막 당기기 N^2
 * 
 * 최대값 갱신 N^2
 * 
 * @author SSAFY
 *
 */
public class boj_12100_2048Easy_최대값갱신 {

	private static final int LEFT = 0, RIGHT = 1, TOP = 2, DOWN=3;
	private static int N, RESULT;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(RESULT < board[i][j]) RESULT = board[i][j];
			}
		}
		push(0, board, LEFT);		
		push(0, board, RIGHT);		
		push(0, board, TOP);		
		push(0, board, DOWN);	
		System.out.println(RESULT);
	}
	
	public static void push(int cnt, int[][] b, int direction) {
		if(cnt >= 5) return;
		
		int[][] board = copy(b);
		
		if(direction == LEFT) {
			for(int i=0; i<N; i++) {
				int std_idx = 0;
				for(int j=1; j<N; j++) {
					if(board[i][std_idx] == 0) std_idx++;
					if(std_idx == j) continue;
					if(board[i][j] == 0) continue;
					if(board[i][std_idx] == board[i][j]) {
						board[i][std_idx] *= 2;
						board[i][j] = 0;
						RESULT = Math.max(RESULT, board[i][std_idx]);
					}
					std_idx = j;
				}
			}
			for(int i=0; i<N; i++) {
				int empty_idx = 0;
				int num_idx = 0;
				while(true) {
					while(empty_idx < N && board[i][empty_idx] != 0) empty_idx++;
					num_idx = empty_idx;
					while(num_idx < N && board[i][num_idx] == 0) num_idx++;
					if(empty_idx >= N || num_idx >= N) break;
					if(empty_idx > num_idx) break;
					board[i][empty_idx] = board[i][num_idx];
					board[i][num_idx] = 0;
				}
			}
		}
		else if(direction == RIGHT) {
			for(int i=0; i<N; i++) {
				int std_idx = N-1;
				for(int j=N-2; j>=0; j--) {
					if(board[i][std_idx] == 0) std_idx--;
					if(std_idx == j) continue;
					if(board[i][j] == 0) continue;
					if(board[i][std_idx] == board[i][j]) {
						board[i][std_idx] *= 2;
						board[i][j] = 0;
						RESULT = Math.max(RESULT, board[i][std_idx]);
					}
					std_idx = j;
				}
			}
			for(int i=0; i<N; i++) {
				int empty_idx = N-1;
				int num_idx = N-1;
				while(true) {
					while(empty_idx >= 0 && board[i][empty_idx] != 0) empty_idx--;
					num_idx = empty_idx;
					while(num_idx >= 0 && board[i][num_idx] == 0) num_idx--;
					if(empty_idx < 0 || num_idx < 0) break;
					if(empty_idx < num_idx) break;
					board[i][empty_idx] = board[i][num_idx];
					board[i][num_idx] = 0;
				}
			}
		}
		else if(direction == TOP) {
			for(int j=0; j<N; j++) {
				int std_idx = 0;
				for(int i=1; i<N; i++) {
					if(board[std_idx][j] == 0) std_idx++;
					if(std_idx == i) continue;
					if(board[i][j] == 0) continue;
					if(board[std_idx][j] == board[i][j]) {
						board[std_idx][j] *= 2;
						board[i][j] = 0;
						RESULT = Math.max(RESULT, board[std_idx][j]);
					}
					std_idx = i;
				}
			}
			for(int j=0; j<N; j++) {
				int empty_idx = 0;
				int num_idx = 0;
				while(true) {
					while(empty_idx < N && board[empty_idx][j] != 0) empty_idx++;
					num_idx = empty_idx;
					while(num_idx < N && board[num_idx][j] == 0) num_idx++;
					if(empty_idx >= N || num_idx >= N) break;
					if(empty_idx > num_idx) break;
					board[empty_idx][j] = board[num_idx][j];
					board[num_idx][j] = 0;
				}
			}
		}
		else if(direction == DOWN) {
			for(int j=0; j<N; j++) {
				int std_idx = N-1;
				for(int i=N-2; i>=0; i--) {
					if(board[std_idx][j] == 0) std_idx--;
					if(std_idx == i) continue;
					if(board[i][j] == 0) continue;
					if(board[std_idx][j] == board[i][j]) {
						board[std_idx][j] *= 2;
						board[i][j] = 0;
						RESULT = Math.max(RESULT, board[std_idx][j]);
					}
					std_idx = i;
				}
			}
			for(int j=0; j<N; j++) {
				int empty_idx = N-1;
				int num_idx = N-1;
				while(true) {
					while(empty_idx >= 0 && board[empty_idx][j] != 0) empty_idx--;
					num_idx = empty_idx;
					while(num_idx >= 0 && board[num_idx][j] == 0) num_idx--;
					if(empty_idx < 0 || num_idx < 0) break;
					if(empty_idx < num_idx) break;
					board[empty_idx][j] = board[num_idx][j];
					board[num_idx][j] = 0;
				}
			}
		}
				
		push(cnt+1, board, LEFT);
		push(cnt+1, board, RIGHT);
		push(cnt+1, board, TOP);
		push(cnt+1, board, DOWN);
	}
	
	public static int[][] copy(int[][] b) {
		int[][] copyBoard = new int[N][N];
		for(int i=0; i<N; i++) {
			System.arraycopy(b[i], 0, copyBoard[i], 0, N);
		}
		return copyBoard;
	}
}