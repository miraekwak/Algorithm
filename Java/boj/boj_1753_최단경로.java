package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1753_최단경로 {

	static int V, E;
	static Node[] adjList;
	static class Node {
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		adjList = new Node[V+1];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		boolean[] visited = new boolean[V+1];
		int[] distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[K] = 0;
		
		int min, min_idx;
		for(int i=1; i<=V; i++) {
			min = Integer.MAX_VALUE;
			min_idx = -1;
			for(int j=1; j<=V; j++) {
				if(visited[j]) continue;
				if(min > distance[j]) {
					min = distance[j];
					min_idx = j;
				}
			}
			if(min_idx == -1) break;
			
			visited[min_idx] = true;
			
			for(Node node = adjList[min_idx]; node != null; node = node.next) {
				if(visited[node.vertex]) continue;
				if(distance[node.vertex] > distance[min_idx] + node.weight) {
					distance[node.vertex] = distance[min_idx] + node.weight;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			sb.append(distance[i] != Integer.MAX_VALUE ? distance[i] : "INF").append("\n");
		}
		System.out.println(sb);
	}

}
