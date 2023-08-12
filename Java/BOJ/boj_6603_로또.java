package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_6603_로또 {
	private static int K;
	private static int[] numbers;
	private static int[] selected;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String line = "";
		while(!(line = br.readLine()).equals("0")) {
			// 입력 처리
			String[] order = line.split(" ");
			K = Integer.parseInt(order[0]);
			numbers = new int[K];
			selected = new int[6];
			for(int i=0; i<K; i++) {
				numbers[i] = Integer.parseInt(order[i+1]);
			}
			// 6개 조합 구하기
			permu(0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	private static void permu(int cnt, int str) {
		if(cnt == 6) {
			for(int i=0; i<6; i++) {
				sb.append(selected[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=str; i<K; i++) {
			selected[cnt] = numbers[i];
			permu(cnt+1, i+1);
		}
	}
}
