package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1244_스위치켜고끄기 {
	private static int N;
	private static int[] switches;
	private static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		switches = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int students = Integer.parseInt(br.readLine());

		for(int s=0; s<students; s++) {
			String[] line = br.readLine().split(" ");
			int gender = Integer.parseInt(line[0]);
			int num = Integer.parseInt(line[1]);
			if(gender == 1) {
				boyAct(num);
			}
			if(gender == 2) {
				girlAct(num);
			}
		}
		
		int idx = 0;
		while(idx < switches.length) {
			if(idx!=0 && idx % 20 == 0) {
				sb.append("\n");
			}
			sb.append(switches[idx]+" ");
			idx++;
		}
		System.out.println(sb);
	}
	
	private static void boyAct(int num) {
		for(int i=0; i<switches.length; i++) {
			if((i+1)%num != 0) continue;
			changeStatus(i);
		}
	}
	
	private static void girlAct(int num) {
		int idx = num -1;
		changeStatus(idx);
		int diff = 1;
		while(idx-diff >= 0 && idx+diff < N) {
			if(switches[idx-diff] != switches[idx+diff]) break;
			
			// ��Ī�̸� ���� ����
			changeStatus(idx-diff);
			changeStatus(idx+diff);
			diff++;

		}
	}
	
	private static void changeStatus(int idx) {
		switches[idx] = (switches[idx] == 1 ? 0 : 1);
	}
}
