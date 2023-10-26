package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_5215_2 {

	private static int[] calorie;
	private static int[] taste;
	private static int N, L, total;
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// �׽�Ʈ ���̽�
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			// �Է�
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			L = Integer.parseInt(line[1]);
			
			// �ʿ��� �迭 �� ���� �ʱ�ȭ
			calorie = new int[N];
			taste = new int[N];
			total = 0;
			
			// ���� Į�θ� �Է�
			for(int i=0; i<N; i++) {
				line = br.readLine().split(" ");
				taste[i] = Integer.parseInt(line[0]);
				calorie[i] = Integer.parseInt(line[1]);
			}
			
			// �ִ� �� ���ϱ�
			search(0, 0, 0, 0); // ���
//			search(); // ��Ʈ����ũ
			
			sb.append("#"+t+" "+total+"\n");
		}
		System.out.println(sb);
	}
	
	/**
	 * ��͸� Ȱ���� �ִ� �� ���ϱ�
	 * @param cnt : ���캻 ��� ����
	 * @param selectedCnt : ������ ��� ����
	 * @param cal : ������� ������ ����� Į�θ� �� 
	 * @param tast : ������� ������ ����� �� ��
	 */
	private static void search(int cnt, int selectedCnt, int cal, int tast) {
		if(cal > L) return;
		if(cnt == N) {
			if(selectedCnt > 0) {
				total = Math.max(total, tast);				
			}
			return;
		}
		search(cnt+1, selectedCnt+1, cal+calorie[cnt], tast+taste[cnt]);
		search(cnt+1, selectedCnt, cal, tast);
	}
	
	/**
	 * ��Ʈ����ŷ�� Ȱ���� �ִ� �� ���ϱ�
	 */
	private static void search() {
		// �κ� ���� ����
		for(int flag=1; flag<=Math.pow(2, N)-1; flag++) {
			int tast =0, cal = 0;
			boolean isTaste = true;
			// �ϳ��� ���տ� ���� ��Ằ�� ���Ǳ�
			for(int bit=0; bit<N; bit++) {
				// ���õ� ����� ���ϱ�
				if((flag & 1<<bit)>0) {
					tast += taste[bit];
					cal += calorie[bit];
				}
				if(cal > L) {
					isTaste = false;
					break;
				}
			}
			// Į�θ��� ���� �ʴ´ٸ� �� �ִ밪 ����
			if(isTaste) {
				total = Math.max(total, tast);
			}
		}
	}
}
