package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1074_Z {
	
	static int N, CNT;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		dfs(R, C, (1<<N));
		System.out.println(CNT);
	}
	
	private static void dfs(int r, int c, int size) {
		if(size == 1) return;
		
		int half = size/2;
		if(r < half && c < half) dfs(r, c, half);
		if(r < half && c >= half) {
			CNT += half * half;
			dfs(r, c - half, half);
		}
		if(r >= half && c < half) {
			CNT += half * half * 2;
			dfs(r-half, c, half);
		}
		if(r >= half && c >= half) {
			CNT += half * half * 3;
			dfs(r-half, c-half, half);
		}
	}
}
