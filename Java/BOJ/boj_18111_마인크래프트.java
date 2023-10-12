package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_18111_마인크래프트 {
	
	static int N, M, B, map[][], TIME, HEIGHT;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int min=Integer.MAX_VALUE, max = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		TIME = Integer.MAX_VALUE;
		for(int height=min; height<=max; height++) {
			flatten(height);
		}
		System.out.println(TIME+" "+HEIGHT);
	}
	
	private static void flatten(int height) {
		int time = 0;
		int block = B;
		int blockNeeds = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] > height) {
					time += (map[i][j] - height)*2;
					block += map[i][j] - height;
				}
				else if(map[i][j] < height) {
					blockNeeds += height - map[i][j];
					time += (height-map[i][j]);
				}
			}
		}
		
		if(block >= blockNeeds) {
			if(time <= TIME) {
				TIME = time;
				HEIGHT = height;
			}
		}
	}
}
