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
 * N , K
 * x -> x-1, x+1, 2*x
 * 
 * 알고리즘]
 * dp
 * dp[x-1] = Math.min(dp[x-1], dp[x] + 1);
 * dp[x+1] = Math.min(dp[x] + 1);
 * dp[x*2] = Math.min(dp[x] + 1);
 * 
 * @author SSAFY
 *
 */
public class boj_1697_숨바꼭질_2 {
	
	private static int N, K;
	private static boolean[] visited;

	static class Location {
		int x;
		int time;
		public Location(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[100_000+1];
		
		if(N >= K) {
			System.out.println(Math.abs(N-K));
		}
		else {
			System.out.println(bfs(N));			
		}
	}
	
	private static int bfs(int start) {
		Queue<Location> queue = new ArrayDeque<>();
		queue.offer(new Location(start, 0));
		visited[start] = true;

		while(!queue.isEmpty()) {
			Location curr = queue.poll();
			if(curr.x == K) {
				return curr.time;
			}
			
			if(curr.x-1 >= 0 && !visited[curr.x-1]) {
				visited[curr.x-1] = true;
				queue.offer(new Location(curr.x-1, curr.time+1));
			}
			
			if(curr.x+1 <=100_000  && !visited[curr.x+1]) {
				visited[curr.x+1] = true;
				queue.offer(new Location(curr.x+1, curr.time+1));
			}
			
			if(curr.x*2 <= 100_000 && !visited[curr.x*2]) {
				visited[curr.x*2] = true;
				queue.offer(new Location(curr.x*2, curr.time+1));
			}
		}
		
		return -1;
	}

}
