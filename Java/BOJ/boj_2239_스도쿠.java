package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 알고리즘]
 * 1. 행, 열별로 확인
 * - 1부터 9까지의 수가 중복되는지 확인 => 9
 * - 행 열별로 반복 => 9*9*9
 * 
 * @author SSAFY
 *
 */
public class boj_2239_스도쿠 {

	static int SIZE=9, ZEROS, map[][];
	static List<Node> zerolist;
	static class Node {
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[SIZE][SIZE];
		zerolist = new ArrayList<Node>();
		for(int i=0; i<SIZE; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<SIZE; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j] == 0) {
					ZEROS++;
					zerolist.add(new Node(i, j));
				}
			}
		}
		
		dfs(0);
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean dfs(int cnt) {
		if(cnt == ZEROS) {
			return true;
		}
		
		Node curr = zerolist.get(cnt);
		for(int i=1; i<=SIZE; i++) {
			if(isExistRow(curr.r, i) || isExistCol(curr.c, i) || isExist3by3(curr.r, curr.c, i)) continue;
			map[curr.r][curr.c] = i;
			if(dfs(cnt+1)) return true;
			map[curr.r][curr.c] = 0;
		}
		return false;
	}
	
	private static boolean isExistRow(int r, int val) {
		for(int i=0; i<SIZE; i++) {
			if(map[r][i] == val) return true;
		}
		return false;
	}

	private static boolean isExistCol(int c, int val) {
		for(int i=0; i<SIZE; i++) {
			if(map[i][c] == val) return true;
		}
		return false;
	}

	private static boolean isExist3by3(int r, int c, int val) {
		for(int i=r/3*3; i<r/3*3+3; i++) {
			for(int j=c/3*3; j<c/3*3+3; j++) {
				if(map[i][j] == val) return true;
			}
		}
		return false;
	}
}
