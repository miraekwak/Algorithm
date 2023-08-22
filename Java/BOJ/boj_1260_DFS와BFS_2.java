package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * DFS와 BFS로 탐색한 결과 출력
 * 방문 후보가 여러개면 작은 번호 먼저 방문
 * 1 ~ N
 * 
 * 출력
 * 첫째줄 DFS 수행 결과
 * 둘째줄 BFS 수행 결과
 * 
 * @author SSAFY
 *
 */
public class boj_1260_DFS와BFS_2 {
	
	private static int N, M;
	private static boolean[][] matrix;
	private static boolean[] visited;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		matrix = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[from][to] = matrix[to][from] = true;
		}
		
		sb = new StringBuilder();
		dfs(V);
		Arrays.fill(visited, false);
		sb.append("\n");
		bfs(V);
		System.out.println(sb);
	}
	
	private static void dfs(int v) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(v);
		
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			if(visited[curr]) continue;
			visited[curr] = true;
			sb.append(curr+" ");
			for(int i=N; i>=1; i--) {
				if(visited[i] || !matrix[curr][i]) continue;
				stack.push(i);
			}
		}
	}
	
	private static void bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(v);
		visited[v] = true;

		while(!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr+" ");
			for(int i=1; i<=N; i++) {
				if(visited[i] || !matrix[curr][i]) continue;
				visited[i] = true;
				queue.offer(i);
			}
		}
	}

}
