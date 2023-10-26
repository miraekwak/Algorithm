package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. dfs 완전 탐색
 * 시간초과
 * 
 * 2. 트리의 root를 정하고 탐색
 * - 트리 root에서 부터 각 leaf노드 까지 탐색
 * - leaf에서 부터 경로값 계산
 * - 각 노드에서 존재하는 서브트리의 최대 경로 값을 가지고 최대 경로 갱신
 * - 최대값을 가지는 서비트리와 두번째 최대값을 가지는 서브트리의 값을 가지고 최대경로 갱신
 * 
 */
public class boj_1167_트리의지름 {

	static Node[] adjList;
	static int MAX_LENGTH;
	static boolean visited[];
	static class Node {
		int v;
		int val;
		Node next;
		public Node(int v, int val, Node next) {
			this.v = v;
			this.val = val;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		adjList = new Node[V+1];
		StringTokenizer st;
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			while(to != -1) {
				int val = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, val, adjList[from]);
				to = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[V+1];
		dfs(1);
		System.out.println(MAX_LENGTH);
	}
	
	public static int dfs(int v) {
		visited[v] = true;
		int max_val1 = 0, max_val2=0;
		for(Node node = adjList[v]; node != null; node = node.next) {
			if(visited[node.v]) continue;
			int temp = dfs(node.v) + node.val;
			if(temp > max_val1) {
				max_val2 = max_val1;
				max_val1 = temp;
			}
			else if(temp > max_val2) {
				max_val2 = temp;
			}
		}
		MAX_LENGTH = Math.max(MAX_LENGTH, max_val1+max_val2);
		return max_val1;
	}
}
