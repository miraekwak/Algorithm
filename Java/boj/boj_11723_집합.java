package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_11723_집합 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			int x;
			switch(order) {
			case "add":
				x = Integer.parseInt(st.nextToken());
				set.add(x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				set.remove(x);
				break;
			case "check":
				x = Integer.parseInt(st.nextToken());
				if(set.contains(x)) sb.append(1+"\n");
				else sb.append(0+"\n");
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				if(set.contains(x)) set.remove(x);
				else set.add(x);
				break;
			case "all":
				for(int num=1; num<=20; num++) set.add(num);
				break;
			case "empty":
				set.clear();
			}
		}		
		System.out.println(sb);
	}
}
