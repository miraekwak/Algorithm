package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 서로소 집합
 *
 * @author SSAFY
 *
 */
public class Solution_3289_서로소집합 {
	private static int N, M;
	private static int[] parents;
	
	private static void make() {
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static int isSameParent(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return 1;
		return 0;
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine().trim());

		StringTokenizer st;
		int cal, a, b;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			make();
			System.out.println(Arrays.toString(parents));
			sb.append("#"+t+" ");
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				cal = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(cal == 0) {
					union(a, b);
					continue;
				}
				sb.append(isSameParent(a, b));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

