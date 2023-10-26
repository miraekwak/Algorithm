package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11724_연결요소의개수 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[N+1];
		boolean[][] connected = new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			connected[from][to] = connected[to][from] = true;
		}
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.add(i);
			while(!queue.isEmpty()) {
				int curr = queue.poll();
				for(int j=1; j<=N; j++) {
					if(visited[j]) continue;
					if(connected[curr][j]) {
						visited[j] = true;
						queue.add(j); 
					}
				}
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
