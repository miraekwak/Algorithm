package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] len = new int[K];
		long total = 0;
		for(int i=0; i<K; i++) {
			total += len[i] = Integer.parseInt(br.readLine());
		}
		long str = 1;
		long end = total/N * 2;
		long result = 0;
		while(str <= end) {
			long mid = (str+end) /2;
			long cnt = 0;
			for(int i=0; i<K; i++) {
				cnt += len[i]/mid;
			}
			if(cnt >= N) {
				result = Math.max(result, mid);
				str = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		System.out.println(result);
	}
}
