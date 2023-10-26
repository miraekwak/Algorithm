package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1247 {

	private static int[] dr; // x ��ǥ
	private static int[] dc; // y ��ǥ
	private static boolean[] visited;
	private static int N, path, dst_r, dst_c;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// �׽�Ʈ ���̽�
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// �Է�
			N = Integer.parseInt(br.readLine().trim());
			dr = new int[N];
			dc = new int[N];
			visited = new boolean[N];
			path = Integer.MAX_VALUE;
			
			String[] line = br.readLine().split(" ");
			int str_r = Integer.parseInt(line[0]); // ����� ��ǥ
			int str_c = Integer.parseInt(line[1]);
			dst_r = Integer.parseInt(line[2]); // ������ ��ǥ
			dst_c = Integer.parseInt(line[3]);
			for(int i=4, j=0; i<line.length; i+=2, j++) {
				dr[j] = Integer.parseInt(line[i]);
				dc[j] = Integer.parseInt(line[i+1]);
			}

			// ���� ��� Ž��
			search(str_r, str_c, 0, 0);
			sb.append("#"+t+" "+path+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * ��͸� �̿��� ���� ��� Ž��
	 * @param r : ���� x��ǥ
	 * @param c : ���� y��ǥ
	 * @param num : ������� �Ÿ� ��
	 * @param idx : ������� ������ ��� ����
	 */
	private static void search(int r, int c, int num, int idx) {
		if(num >= path) return; // �Ÿ����� �ּ� �Ÿ� �պ��� Ŭ ��� Ž�� ����
		if(idx == N) {
			path = Math.min(path, num+distance(r, c, dst_r, dst_c));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			search(dr[i], dc[i], num+distance(r, c, dr[i], dc[i]), idx+1);
			visited[i] = false;
		}
	}
	
	/**
	 * �Ÿ� ��� �Լ�
	 * @param r1 : ����� x��ǥ
	 * @param c1 : ����� y��ǥ
	 * @param r2 : ������ x��ǥ
	 * @param c2 : ������ y��ǥ
	 * @return
	 */
	private static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) +  Math.abs(c1 - c2);
	}

}
