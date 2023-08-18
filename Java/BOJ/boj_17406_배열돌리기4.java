package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 배열 A(NxM)
 * 배열 A 값 = 각 행의 합 중 최솟값
 * 
 * 회전 연산>
 * (r,c,s)
 * 좌상 r-s, c-s
 * 우하 r+s, c+s
 * 
 * 회전 시 껍질 별 회전
 * 
 * 출력]
 * 회전 연산 순서별 배열 A의 값 중 최솟값
 * 
 * 연산 횟수 K는 최대 6
 * 6 순열 => 6! = 720
 * 
 * 회전 연산 껍질별 2N+2M정도? => 200
 * 
 * 720 x 200
 * 
 * 
 * 알고리즘 >
 * 껍질별 회전 연산
 * 순열 
 * 최솟값 계산
 * 
 * 
 * 
 * @author SSAFY
 *
 */

public class boj_17406_배열돌리기4 {
	
	static class Calculation {
		int r;
		int c;
		int s;
		public Calculation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	private static int N, M, K, ARR_SUM;
	private static int arr[][], change[][], numbers[];
	private static boolean selected[];
	private static Calculation[] cals;
	private static int[] dr = {1, 0, -1, 0}; // 하우상좌
	private static int[] dc = {0, 1, 0, -1}; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		change = new int[N][M];
		cals = new Calculation[K];
		selected = new boolean[K];
		numbers = new int[K];
		ARR_SUM = 100*N*M;
		
		for(int j=0; j<K; j++) {
			st = new StringTokenizer(br.readLine());
			cals[j] = new Calculation(
						Integer.parseInt(st.nextToken())-1, // r
						Integer.parseInt(st.nextToken())-1, // c
						Integer.parseInt(st.nextToken())  // s
					);
		}
		
		permu(0);
		System.out.println(ARR_SUM);
	}
	
	private static void permu(int cnt) {
		if(cnt == K) {
			copy();
			for(int i=0; i<K; i++) {
				rotation(cals[numbers[i]]);				
			}
			updateMinSum();
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(selected[i]) continue;
			numbers[cnt] = i;
			selected[i] = true;
			permu(cnt+1);
			selected[i] = false;
		}
	}
	
	private static void copy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				change[i][j] = arr[i][j];
			}
		}
	}
	
	private static void rotation(Calculation cal) {
		int row = cal.r, col = cal.c, s = cal.s;
		int r, c, d;
		for(int i=0; i<cal.s; i++) {
			int str_r = row - s + i;
			int str_c = col - s + i;
			int end_r = row + s - i;
			int end_c = col + s - i;
			r = str_r;
			c = str_c;
			d = 0;
			int temp = change[r][c];
			while(r != str_r || c != str_c+1) {
				int next_r = r+dr[d];
				int next_c = c+dc[d];
				if(next_r < str_r || next_r > end_r || next_c < str_c || next_c > end_c) {
					d++;
					continue;
				}
				change[r][c] = change[next_r][next_c];
				r = next_r;
				c = next_c;
			}
			change[r][c] = temp;
		}
	}
	
	private static void updateMinSum() {
		int sum = 100*N*M;
		for(int i=0; i<N; i++) {
			sum = Math.min(sum, Arrays.stream(change[i]).sum());
		}
		ARR_SUM = Math.min(ARR_SUM, sum);
	}
	
	private static void print() {
		for(int[] a: change) {
			System.out.println(Arrays.toString(a));				
		}
		System.out.println();
	}
}
