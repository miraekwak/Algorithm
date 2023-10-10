package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * N명의 사람(1~N)
 * 서로 아는 관계 or 몇사람을 거쳐 아는 관계 => 하나의 무리 
 * 몇개의 무리가 존재하는지 계산
 * 
 * 입력]
 * 1<=N<=100
 * 0<=M<=N(N-1)/2
 * 
 * 알고리즘] - union-find
 * N명의 사람에 대해 연결 여부로 union-find 진행
 * 부모가 다 찾아지면 무리 계산
 * - 부모가 자신인 사람에 대해 갯수 세기
 * 
 * 시간복잡도]
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_7465_창용마을무리의개수 {

	static int N, M, parent[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		parent = new int[101];
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			makeDisjointSet();
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			sb.append("#"+tc+" "+count()+"\n");
		}
		System.out.println(sb);
	}
	
	private static void makeDisjointSet() {
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot == broot) return;
		parent[broot] = aroot;
	}
	
	private static int count() {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(parent[i] == i) cnt++;
		}
		return cnt;
	}
}
