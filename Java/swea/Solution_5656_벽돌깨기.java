package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 알고리즘]
 * 12(W)*15(H) 칸 중에 4(N)개 조합 
 * 선택된 칸에 대해 연쇄적으로 0으로 마킹 - DFS
 * 모든 배열에서 0으로 된 값을 제외하고 아래로 당기기
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_5656_벽돌깨기 {

	static int N, W, H, REMAIN, original[][];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Block {
		int r, c, val;
		public Block(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			original = new int[H][W];
			REMAIN = H*W;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					original[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			selectBlock(0, original);
			sb.append("#"+tc+" "+REMAIN+"\n");
		}
		System.out.println(sb);
	}
	
	private static void selectBlock(int cnt, int[][] map) {
		if(cnt == N) {
			REMAIN = Math.min(REMAIN, count(map));
			return;
		}
		
		List<Block> selected = new ArrayList<Block>();
		for(int j=0; j<W; j++) {
			int idx = 0;
			while(idx < H && map[idx][j] == 0) idx++;
			if(idx < H) selected.add(new Block(idx, j, map[idx][j]));
		}

		if(selected.size() == 0) {
			REMAIN = Math.min(REMAIN, count(map));
			return;
		}
		for(int i=0; i<selected.size(); i++) {
			int[][] changedMap = game(selected.get(i), map);
			selectBlock(cnt+1, changedMap);
		}
	}

	private static int[][] game(Block block, int[][] arr) {
		int[][] map = copy(arr);
		map = bomb(block.r, block.c, map);
		
		for(int j=0; j<W; j++) {
			int zero = H-1;
			int num = H-2;
			while(zero > num) {
				while(zero >= 0 && map[zero][j] != 0) zero--;
				num = zero;
				while(num >= 0 && map[num][j] == 0) num--;
				if(zero < 0|| num < 0 || zero <= num) break;
				map[zero][j] = map[num][j];
				map[num][j] = 0;
			}
		}
		return map;
	}
	
	private static int count(int[][] map) {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j] > 0) cnt++;
			}
		}
		return cnt;
	}
	
	private static int[][] bomb(int r, int c, int[][] map) {
		int len = map[r][c];		
		map[r][c] = 0;
		
		for(int d=0; d<4; d++) {
			int nr = r;
			int nc = c;
			for(int l=1; l<len; l++) {
				nr += dr[d];
				nc += dc[d];
				if(isOutOfRange(nr, nc) || map[nr][nc] == 0) continue; 
				map = bomb(nr, nc, map);
			}
		}		
		return map;
	}

	private static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= H || c < 0 || c >= W;
	}
	
	private static int[][] copy(int[][] arr) {
		int[][] temp = new int[H][W];
		for(int i=0; i<H; i++) {
			System.arraycopy(arr[i], 0, temp[i], 0, W);
		}
		return temp;
	}
	
	private static void print(int[][] map) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
