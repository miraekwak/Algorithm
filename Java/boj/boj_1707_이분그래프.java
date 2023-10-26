package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1707_이분그래프 {

	static int K, V, E;
	static int isTeamA[];
	static Node[] adjList;
	
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
		K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			isTeamA = new int[V+1];
			adjList = new Node[V+1];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if(from == to) continue;
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}
			sb.append(isBipartiteGraph()?"YES":"NO").append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean isBipartiteGraph() {
		for(int i=1; i<=V; i++) {
			if(isTeamA[i] != 0) continue;
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.add(i);
			isTeamA[i] = 1;
			while(!queue.isEmpty()) {
				int curr = queue.poll();
				for(Node node = adjList[curr]; node != null; node = node.next) {
					if(isTeamA[node.v] == isTeamA[curr]) return false;
					if(isTeamA[node.v] != 0) continue; // 이미 방문함
					isTeamA[node.v] = (isTeamA[curr] == 1 ? 2 : 1);
					queue.offer(node.v);
				}
			}			
		}
		return true;
	}
}
