package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * NxN 도시
 * r,c 는 1,1부터 시작
 * 치킨거리 : 집과 가장 가까운 치킨집 사이 거리
 * 도시 치킨 거리 : 모든 집의 치킨 거리 합
 * 거리 : 유클리드 공식
 * 0 빈칸, 1 집, 2 치킨집
 * 
 * 도시 치킨 거리가 가장 작은 치킨집 M개의 도시 치킨 거리 값
 * 
 * 1.치킨 집 조합 후 최소 치킨 거리 구하기
 * 치킨집 중에 M개를 선택
 * 	- 유클리드 거리계산
 * 
 *2.  
 * 
 * @author SSAFY
 *
 */
public class boj_15686_치킨배달 {
	
	private static int N, M, min_chicken_path=Integer.MAX_VALUE, SIZE=0;
	private static int[][] map;
	private static Chicken[] selected, house;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][N];
		house = new Chicken[13];
		selected = new Chicken[M];
		
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j] == 2) house[SIZE++] = new Chicken(i, j); 
			}
		}
		
		chooseChickenHouse(0, 0);
		System.out.println(min_chicken_path);
	}
	
	/**
	 * 
	 * @param r_str
	 * @param c_str
	 * @param cnt
	 */
	private static void chooseChickenHouse(int cnt, int str) {
		if(cnt > M) return;
		if(cnt == M) {
			calculateChickenPath();
			return;
		}
		
		for(int i=str; i<SIZE; i++) {
			selected[cnt] = house[i];
			chooseChickenHouse(cnt+1, i+1);
		}
	}
	
	private static void calculateChickenPath() {
		int total_path = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 1) continue;
				int path = Integer.MAX_VALUE;
				for(int k=0; k<M; k++) {					
					path = Math.min(path, distance(i, j, selected[k].row, selected[k].col));
				}
				total_path += path;
			}
		}
		if(total_path != 0) {
			min_chicken_path = Math.min(min_chicken_path, total_path);			
		}
	}
	
	private static int distance(int r, int c, int y, int x) {
		return Math.abs(r-y) + Math.abs(c-x);
	}
}

class Chicken{
	int row;
	int col;
	public Chicken(int r, int c) {
		this.row = r;
		this.col = c;
	}
}
