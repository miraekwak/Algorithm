package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj_1927_최소힙 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int i = 1; i <= N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(queue.isEmpty()) sb.append(0+"\n");
				else sb.append(queue.poll()+"\n");
			} else {
				queue.add(x);
			}
		}		
		System.out.println(sb);
	}
}
