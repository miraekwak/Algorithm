package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954_2 {

	private static int[] dr = {0, 0, 1, 0, -1};
	private static int[] dc = {0, 1, 0, -1, 0};
	private static int[][] map;
	private static int N;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// �׽�Ʈ ���̽�
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// �Է�
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			
			// �����̼���
//			makeSnail(); // �ݺ��� �Լ�
			makeSnail(0,0,1,1); // ����Լ�
			
			// ���
			sb.append("#"+t+"\n");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(map[i][j]+" ");
				}
				sb.append("\n");
			}
			
		}
		System.out.println(sb);
	}
	
	/**
	 * �ݺ��� ����ؼ� Ǯ��
	 * @param n : �� ũ��
	 */
	private static void makeSnail() {
		int r = 0, c = 0;
		int d = 1;
		int curr = 1;

		while(true) {
			map[r][c] = curr;
			if(curr==N*N) break;
			
			int next_r = r + dr[d];
			int next_c = c + dc[d];
			
			// ������ ����ų� �̹� ���ڰ� ä���� ��� ���� �ٲٱ�
			if(next_r<0 || next_r>=N || next_c<0 || next_c>=N || map[next_r][next_c] != 0) {
				d = (d+1) % 5;
				continue;
			}
			r = next_r;
			c = next_c;
			curr++;
		}
	}
	
	/**
	 * ��͸� ����ؼ� Ǯ��
	 * @param r : ���� �� ��ġ
	 * @param c : ���� �� ��ġ
	 * @param d : ���� ���� (��->��->��->��->..)�� �ϳ�
	 * @param num : ���� ����
	 */
	private static void makeSnail(int r, int c, int d, int num) {
		map[r][c] = num;
		if(num == N*N) {
			return;
		}
		
		int next_r = r + dr[d];
		int next_c = c + dc[d];
		
		// ������ ����ų� �̹� ���ڰ� ä���� ��� ���� �ٲٱ�
		if(next_r<0 || next_r>=N || next_c<0 || next_c>=N || map[next_r][next_c] != 0) {
			makeSnail(r, c, (d+1) % 5, num);
		}
		else {
			makeSnail(next_r, next_c, d, num+1);
		}
		
	}
}
