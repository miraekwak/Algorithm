package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2630_색종이만들기 {
	
	static int N, BCNT, WCNT;
	static boolean isBlue[][];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		isBlue = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) isBlue[i][j] = true;
			}
		}
		dfs(0, 0, N);
		System.out.println(WCNT+"\n"+BCNT);
	}
	
	private static void dfs(int r, int c, int size) {
		if(isAllSame(r, c, size)) {
			if(isBlue[r][c]) BCNT++;
			else WCNT++;
			return;
		}
		
		int half = size/2;
		dfs(r, c, half);
		dfs(r, c + half, half);
		dfs(r+half, c, half);
		dfs(r+half, c+half, half);
	}
	
	private static boolean isAllSame(int r, int c, int size) {
		boolean isB = isBlue[r][c];
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(isB ^ isBlue[i][j]) return false; 
			}
		}
		return true;
	}
}
