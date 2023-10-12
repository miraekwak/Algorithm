package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_1764_듣보잡 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> persons = new HashMap<String, Integer>();
		for (int i = 1; i <= N; i++) {
			persons.put(br.readLine(), 1);
		}
		List<String> names = new ArrayList<String>();
		for(int i=0; i<M; i++) {
			String p = br.readLine();
			if(persons.containsKey(p)) {
				names.add(p);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(names.size()+"\n");
		Collections.sort(names);
		for(String person: names) {
			sb.append(person+"\n");
		}
		System.out.println(sb);
	}
}
