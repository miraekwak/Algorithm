package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_4012 {

	private static int[][] senerge;
	private static boolean[] visited;
	private static int N, tasteDiff;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// �׽�Ʈ ���̽�
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// �Է�
			N = Integer.parseInt(br.readLine().trim());
			
			senerge = new int[N][N];
			visited = new boolean[N];
			tasteDiff = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				String[] line = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					senerge[i][j] = Integer.parseInt(line[j]);					
				}
			}
			
			// �ּ� �� ���� ã��
			choose(0, 0, N/2);
			
			sb.append("#"+t+" "+tasteDiff+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * ������ �̿��� �� �������� ������ �ּ� ������ ���ϴ� �Լ�
	 * @param cnt : ������� ������ ��� ����
	 * @param str : ������ �� �ִ� idx ���� ��ġ
	 * @param r : �� ������ �� �ִ� ��� ����
	 */
	private static void choose(int cnt, int str, int r) {
		if(cnt == r) {
			tasteDiff = Math.min(tasteDiff, getDiff());				
			return;
		}
		for(int i=str; i<N; i++) {
			visited[i] = true;
			choose(cnt+1, i+1, r);
			visited[i] = false;			
		}
	}
	
	/**
	 * ���� ���θ� ���� �� ���İ� �� ���� ���ϴ� �Լ�
	 * @return �� ���� �� �� ����
	 */
	private static int getDiff() {
		int diff = 0;
		for(int i=0; i<N; i++) {
			// true �Ǵ� false�� ���� ���� A, B�� ����
			boolean food = visited[i];
			// �ó����� �� �� �ִ� ���� ���� ��� Ž��
			for(int j=i+1; j<N; j++) {
				if(food == visited[j]) {
					// ���� ���� ���
					diff += food ? senerge[i][j]+senerge[j][i] : -(senerge[i][j]+senerge[j][i]); 
				}
			}
		}
		return Math.abs(diff);
	}
}
