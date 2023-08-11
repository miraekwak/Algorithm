package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * 연산
 * 1. 배열에 정수 x(x!=0)을 넣기
 * 2. 배열에서 절댓값이 가장 작은 값 출력, 제거
 * 		- 가장 작은 값이 여러개일 때는 가장 작은 수를 출력하고 제거
 * 
 * 
 * 1. 배열에서 찾기
 * 삽입삭제 1
 * 절댓값 가장 작은 값 탐색 N
 * 연산의 개수 N
 * => O(N^2) => 10_000_000_000 백억 1초동안 불가
 * 
 * 2. 우선순위 큐를 사용
 * 최소힙을 사용하여 구현
 * comparable 구현하여 조건 만족
 * 
 * 삽입삭제  O(logN)
 * 연산 개수 N(1<=N<=100_000) -> O(NlogN)
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_11286_절댓값힙 {
	
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Comparator<Integer> c = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}

				return Math.abs(o1) - Math.abs(o2);
			}
		};
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(c);

		StringBuilder sb = new StringBuilder();		
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x==0) {
				if(queue.isEmpty()) sb.append("0"+"\n");
				else sb.append(queue.poll()+"\n");
				continue;
			}
			queue.add(x);
		}
		
		System.out.println(sb);
	}
}
