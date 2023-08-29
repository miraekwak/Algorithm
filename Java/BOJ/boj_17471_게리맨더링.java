package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 출력]
 * 인구수 차이 최소값
 * 
 * 알고리즘]
 * 1. N개의 정점으로 부터 시작하는 서로소 집합
 * 	- 연결된 정점에 대해 union 해가며 집합에 포함 
 * 	- 최대 N/2까지
 * 	- 집합을 만들었을 때 각 집합에 대해 연결 판단 
 * 
 * 2. 부분집합 구하고 부분집합 내에 연결 파악 하기
 * 	- boolean 배열을 통해 부분 집합 생성
 * 	- true or false의 부분집합에 대해 연결 파악 -> bfs
 * 
 * @author SSAFY
 *
 */
public class boj_17471_게리맨더링 {
	
	static int N, num[];
	static Node[] adjList;
	
	static class Node{
		int n;
		Node next;
		public Node(int n, Node next) {
			this.n = n;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new Node[N];
		int cnt, to;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				to = Integer.parseInt(st.nextToken())-1;
				adjList[i] = new Node(to, adjList[i]);				
			}
		}
		System.out.println(getMinPopulation());
	}
	
	public static int getMinPopulation() {
		int pop = Integer.MAX_VALUE;
		boolean isPossible = false;
		// 공집합, 전체집합 제외한 부분집합 생성
		for(int i=1; i<(1<<N)/2; i++) {
			int result = getPopulation(i);
			if(result == -1) continue;
			pop = Math.min(pop, result);
			isPossible = true;
		}
		return isPossible ? pop : -1;
	}
	
	public static int getPopulation(int flag) {
		int A_sum = 0, B_sum = 0; 
		int size=0, idx=0;
		for(int i=0; i<N; i++) {
			if((flag & (1<<i)) != 0) {
				size++;
				idx = i;
			}
		}
		A_sum = bfs(idx, size, flag, true);
		for(int i=0; i<N; i++) {
			if((flag & (1<<i)) == 0) {
				B_sum = bfs(i, N-size, flag, false);
				break;
			}
		}

		if(A_sum == -1 || B_sum == -1) {
			return -1;
		}
		return Math.abs(A_sum-B_sum);
	}
	
	public static int bfs(int str, int size, int flag, boolean isA) {
		int cnt=0, sum = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		queue.offer(str);
		visited[str] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			sum += num[curr];
			if(++cnt == size) return sum;
			
			for(Node temp=adjList[curr]; temp!=null; temp=temp.next) {
				if(visited[temp.n]) continue;
				if(isA) {
					if((flag&(1<<temp.n)) != 0) {
						queue.offer(temp.n);
						visited[temp.n] = true;
					}
				}
				else {
					if((flag&(1<<temp.n)) == 0) {
						queue.offer(temp.n);
						visited[temp.n] = true;
					}
				}
			}
		}
		return -1;
	}
	
}
