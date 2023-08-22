package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 *
 */
public class boj_2252_줄세우기 {
	
	private static int N, M;
	private static List<Integer>[] adjList;
	private static int[] degrees;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new List[N+1];
		degrees = new int[N+1];
		for(int i=1; i<=N; i++) {
        	adjList[i] = new ArrayList<>();
        }
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	adjList[from].add(to);
        	degrees[to]++;
		}
		sb = new StringBuilder();
		BFS();
		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(degrees[i]==0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			sb.append(curr+" ");
			
			for(int to: adjList[curr]) {
				if(--degrees[to] == 0) queue.offer(to);
			}
		}
	} 
}
