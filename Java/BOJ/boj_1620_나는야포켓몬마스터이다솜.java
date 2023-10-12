package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_1620_나는야포켓몬마스터이다솜 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[] pocket = new String[N+1];
		Map<String, Integer> pocketList = new HashMap<String, Integer>();
		for (int i = 1; i <= N; i++) {
			pocket[i] = br.readLine();
			pocketList.put(pocket[i], i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String order = br.readLine();
			if(order.matches("^[0-9]*$")) {
				sb.append(pocket[Integer.parseInt(order)]+"\n");
			} else {
				sb.append(pocketList.get(order)+"\n");
			}
		}
		System.out.println(sb);
	}
}
