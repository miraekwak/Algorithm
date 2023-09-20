package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11438_LCA2 {

	private static Node[] list;
	private static int[][] parent;
	private static int N, K, depth[];

	static class Node {
		int v;
		Node next;
		public Node(int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new Node[N+1];
		StringTokenizer st;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list[v1] = new Node(v2, list[v1]);
			list[v2] = new Node(v1, list[v2]);
		}
		
		K= 1;
		for(int i=1; i<=N; i*=2) {
			K++;
		}
		
		depth = new int[N+1];
		parent = new int[K][N+1];
		init(1, 1, 0);
		fillParent();

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			sb.append(lca(v1, v2)).append("\n");
		}
		System.out.println(sb);
	}

	private static void fillParent() {
		for(int i=1; i<K; i++) {
			for(int j=1; j<=N; j++) {
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
	}

	private static void init(int v, int d, int pa) {
		depth[v] = d;
		parent[0][v] = pa;
		
		for(Node child = list[v]; child != null; child = child.next) {
			if(child.v == pa) continue;
			init(child.v, d+1, v);
		}
	}
	
	private static int lca(int a, int b) {
		// a를 더 깊은 노드로 선택
		if(depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		// 트리 높이 맞추기
		// 높이 차이가 5라면 2^2 + 2^0 만큼 이동
		// 큰 단위의 높이부터 이동하기 위해 K-1에서부터 탐색
		for(int i=K-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parent[i][a];
			}
		}
		
		if(a == b) return a;
		
		// 둘의 부모가 일치하는 지점을 지나치고 부모가 달라지는 높이 찾음
		// 달라진 부모로 이동하여 다시 확인 
		for(int i=K-1; i>=0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		// 결론적으로 a와 b는 부모가 같아진 자식이 남아있음
		// 따라서 바로 위의 부모를 반환
		return parent[0][a];
	}
}