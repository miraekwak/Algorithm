package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제]
 * 최대 6단계 이내 연결
 * 케빈 베이컨의 수
 * - 기준부터 다른 모든 사람까지의 케빈 베이컨의 수의 합
 * 케빈 베이컨의 수가 가장 작은 사람 구하기
 * 
 * 입력]
 * 2<=N<=100 유저의 수
 * 1<=M<=5000 관계의 수
 * 
 * 
 * 알고리즘]
 * 1. bfs 탐색
 * 모든 유저에서 bfs로 나머지 정점까지의 단계 찾기
 * 100 * 100
 * 
 * 
 */
public class boj_1389_케빈베이컨의6단계법칙 {
	private static int N, M, min, num;
	private static boolean adjMatrix[][], visited[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjMatrix = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = adjMatrix[b][a] = true;
		}
		min = Integer.MAX_VALUE;
		visited = new boolean[N+1];
		for (int v = 1; v <=N; v++) {
			Arrays.fill(visited, false);
			bfs(v);
		}
		System.out.println(num);
	}

	private static void bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(v);
		visited[v] = true;
		int depth = 0;
		int sum = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				int cur = queue.poll();
				sum += depth;
				for(int j=1; j<=N; j++) {
					if(!adjMatrix[cur][j] || visited[j]) continue;
					queue.add(j);
					visited[j] = true; 
				}
			}
			depth++;
		}
		if(min > sum) {
			min = sum;
			num = v;
		}
	}
}
