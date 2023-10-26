package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author SSAFY
 *
 */
public class Solution_3124_최소스패닝트리_kruskal {
	
	static class Edge implements Comparable<Edge>{
		int a, b, w;
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	private static int V, E;
	private static int[] parents;
	private static Edge[] edgeList;
	
	private static void make() {
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
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
		int a, b, weight;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			parents = new int[V+1];
			edgeList = new Edge[E];
			make();
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(a, b, weight);
			}
			Arrays.sort(edgeList);
			
			long result = 0;
			int cnt = 0;
			for(Edge e : edgeList) {
				if(union(e.a, e.b)) {
					result += e.w;
					if(++cnt == V-1) break;
				}
			}
			sb.append("#"+t+" "+result).append("\n");
		}
		System.out.println(sb);
	}
}

