package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 9개의 줄에 1보다 크거나 같고 99보다 작거나 같은 자연수
 * 
 * 조합
 * 9명 중에 2명을 선택하는 조합 9C2
 * 9개의 모자의 합 = S
 * 각 조합에 대해 S에서 해당하는 모자값을 빼서 확인
 * 
 * 
 * @author SSAFY
 *
 */
public class boj_3040 {
	
	private static int sum, numbers[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		numbers = new int[9];
		for(int i=0; i<9; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
			sum+= numbers[i];
		}
		selectDwarf(0, 0, 0);
	}
	
	private static void selectDwarf(int cnt, int str, int flag) {
		if(cnt == 2) {
			int subSum = 0;
			for(int i=0; i<9; i++) {
				if((flag & 1<<i) != 0) subSum += numbers[i];
			}
			
			if(sum-subSum == 100) {
				for(int i=0; i<9; i++) {
					if((flag & 1<<i) == 0) System.out.println(numbers[i]);;
				}
				return;
			}
		}
		
		for(int i=str; i<9; i++) {
			selectDwarf(cnt+1, i+1, flag | 1<<i);
		}
	}
}
