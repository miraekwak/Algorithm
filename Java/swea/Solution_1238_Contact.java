package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알고리즘] BFS
 * - 시작 당번부터 너비를 비교하며 차례로 탐색
 * - 뒤에 이어질 사람이 있다면 지속
 * - 아니라면 남은 사람 중에 제일 값이 큰 사람 반환
 *
 * @author SSAFY
 *
 */
public class Solution_1238_Contact {
	private static int N, START;
	private static boolean contact[][], visited[];
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스
		int T = 10;

		StringTokenizer st;
		int from, to;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			START = Integer.parseInt(st.nextToken());
			contact = new boolean[101][101];
			visited = new boolean[101];
			st = new StringTokenizer(br.readLine());			
			for(int i=0; i<N; i+=2) {
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				contact[from][to] = true;
			}
			
			sb.append("#"+t+" "+call()+"\n");
		}
		System.out.println(sb);
	}
	
	private static int call() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(START);
		visited[START] = true;
		
		int sub_max = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			sub_max = 0;
			while(size-- > 0) {
				int curr = queue.poll();
				sub_max = Math.max(sub_max, curr);
				for(int i=1; i<=100; i++) {
					if(curr == i) continue;
					if(visited[i]) continue;
					if(contact[curr][i]) {
						queue.add(i);
						visited[i] = true;
					}
				}
			}
		}
		return sub_max;
	}
}

