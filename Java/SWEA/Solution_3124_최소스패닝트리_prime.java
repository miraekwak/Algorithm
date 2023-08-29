package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author SSAFY
 *
 */
public class Solution_3124_최소스패닝트리_prime {
	
	static class Node implements Comparable<Node> {
		int v, w;
		Node next;
		public Node(int v, int w, Node next) {
			this.v =v;
			this.w = w;
			this.next=next;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(w, o.w);
		}
	}
	
	private static int V, E;
	private static int minEdge[];
	private static boolean[] visited;
	private static Node[] adjList;
	
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
			adjList = new Node[V];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				weight = Integer.parseInt(st.nextToken());
				adjList[a] = new Node(b, weight, adjList[a]);
				adjList[b] = new Node(a, weight, adjList[b]);
			}
			sb.append("#"+t+" "+getWeight(0)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static long getWeight(int start) {
		minEdge = new int[V];
		visited = new boolean[V];
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[start] = 0;
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, minEdge[start], null));

		long total = 0, cnt = 0;
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			if(visited[curr.v]) continue;
			visited[curr.v] = true;
			total += curr.w;
			
			if(++cnt == V) break;
			
			for(Node temp=adjList[curr.v]; temp!=null; temp=temp.next) {
				if(visited[temp.v]) continue;
				if(minEdge[temp.v] > temp.w) {
					minEdge[temp.v] = temp.w;
					queue.add(new Node(temp.v, minEdge[temp.v], null));
				}
			}
		}
		return total;
	}
}

