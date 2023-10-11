package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간]
 * 시작 : 3:30
 * 
 * 문제]
 * N개의 점들을 움직여 0,0으로 집합
 * i번째 움직임에서 상하좌우로 i만큼의 거리 이동
 * 최소 몇번의 움직임으로 모든 점을 원점에 모으는가
 * 
 * 입력]
 * 1<=N<=10
 * -10^9<=x, y<=10^9
 * 
 * 알고리즘] 
 * 점 x, y에서 원점으로의 유클리디안 거리 구하기
 * 모든 점이 짝수 or 모든 점이 홀수가 아니면 구할 수 없음
 * 가장 먼 점에서의 거리 구하기
 * 해당 거리를 만족하는 i구하기
 * - i번째까지 거리의 합은 해당 거리보다 커야함
 * - 거리까지 이동 후 짝수만큼의 거리가 남아야 왔던길을 되돌아가서 원점에 있을수있음
 * - ex) 11이라고 하자 i=6일때 21의 값으로 11보다 큼
 * -     남은 거리는 10으로 11만큼 이동해 원점에 도착한 후 5만큼 이동 후 다시 5만큼 이동해 원점으로 되돌아오기
 * 
 * 
 * 시간복잡도]
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_8458_원점으로집합 {

	static int N;
	static long max;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 0번
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			long num = max = Math.abs(x) + Math.abs(y);
			boolean isAllSame = true; 
			boolean isEven = num %2 == 0;
			for(int i=1; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				num = Math.abs(x) + Math.abs(y);
				if(isAllSame && isEven != (num%2 == 0)) {
					isAllSame = false;
				}
				max = Math.max(max, num);
			}
			if(isAllSame) {
				long result = 0;
				long sum = 0;
				while(true) {
					if(sum >= max && Math.abs(sum-max)%2 == 0) break;
					result++;
					sum += result;
				}
				sb.append("#"+tc+" "+result+"\n");
			}
			else {
				sb.append("#"+tc+" "+(-1)+"\n");	
			}
		}
		System.out.println(sb);
	}
}
