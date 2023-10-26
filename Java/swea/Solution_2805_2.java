package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_2 {

	private static int[][] map;
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			// �Է�
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split("");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(line[j]);
				}
			}
			
			// ���۹� ��Ȯ
			int total = getRevenue(N);
			sb.append("#"+t+" "+total+"\n");
		}
		System.out.println(sb);
	}
	
	private static int getRevenue(int size) {
		int total = 0; // ���۹� ����
		int row = 0; // ���� ��
		int pass = size/2; // 0�࿡�� ��Ȯ ������ġ
		
		// ���� ���� ���۹� ��Ȯ
		for(int i=1; i<=size; i+=2) {
			for(int j=pass; j<pass+i; j++) {
				total += map[row][j];
			}
			pass--;
			row++;
		}
		
		// �Ʒ��� ���� ���۹� ��Ȯ
		pass = 1; // �߾� -1���� ��Ȯ ���� ��ġ
		for(int i=size-2; i>=1; i-=2) {
			for(int j=pass; j<pass+i; j++) {
				total += map[row][j];
			}
			pass++;
			row++;
		}
		return total;
	}
}
