package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 알고리즘]
 * N명의 사람이 존재할 때 N-1개의 간선을 통해 모든 사람이 연결이 되는지 확인
 * N명의 사람에 대해 각각 시작해서 - DFS
 * 	- 연결된 다음 사람 탐색
 * 	- 다음 사람이 있으면 다음 사람으로 이동 없으면 종료
 * 	- 다음 사람이 없을 때 n명의 사람을 선택했다면 1 아니면 0 반환
 *  
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_13023_ABCDE {

	private static int N, M;
	private static Node[] adjList;
	private static boolean[] visited;
	
	static class Node { // 시작 위치, 끝위치 저장을 위한 클래스
		int v;
		Node next;

		public Node(int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 

		adjList = new Node[N];
		visited = new boolean[N];
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		System.out.println(isExist()?1:0);
	}
	
	private static boolean isExist() {
		for(int i=0; i<N; i++) {
			if(dfs(1, i)) return true;
		}
		return false;
	}
	
	private static boolean dfs(int cnt, int v) {
		visited[v] = true;
		if(cnt >= 5) {
			return true;
		}
		
		for(Node temp = adjList[v]; temp != null; temp = temp.next) {
			if(visited[temp.v]) continue;
			if(dfs(cnt+1, temp.v)) return true;
		}
		visited[v] = false;
		return false;
	}
}
