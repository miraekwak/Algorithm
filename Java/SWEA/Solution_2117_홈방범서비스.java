package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간]
 * 시작 : 1:30
 * 종료 : 3:30
 * 
 * 문제]
 * N*N
 * 마름모 영역에서 제공됨
 * 서비스 영역의 크기 K가 커질 수록 운영 비용 커짐
 * 운영 비용은 서비스 영역의 면적
 * 운영 비용 = K * K + (K - 1) * (K - 1)
 * 영역을 벗어난 영역에 서비스 제공해도 운영비용 동일
 * 서비스 제공받는 집은 M 비용 지불
 * 손해를 보지 않는 한 최대한 많은 집에 제공
 * 서비스를 가장 많은 집들에게 제공하는 영역을 찾고 제공받는 집들의 수 출력
 * 
 * 입력]
 * T 테스트 케이스 개수
 * N 도시크기 5<=N<=20
 * M 1<=M<=10
 * 1: 집위치 
 * 
 * 
 * 알고리즘] 
 * 지도의 각 위치에서 K범위에 따라 달라지는 이익값 계산
 * 지도 위치에서 가능한 K 1~N
 * 1. 범위내 집 계산
 * 	  - 기존 집 개수보다 작다면 넘어가기
 * 	  - 이전 K에서의 집의 개수 활용하기
 * 2. 운영비용 계산
 * - 이익 >= 0 인 경우 집 개수 갱신
 *  
 * 시간복잡도]
 * 20*20 (N*N)
 * *20 (K)
 * *탐색 (K*K+(K-1)*(K-1))
 * => 6,008,000 3초 이내 가능할듯?
 * 
 * @author SSAFY
 *
 */
public class Solution_2117_홈방범서비스 {

	static int N, M, CNT, operatingCosts[];
	static boolean isHouse[][];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			isHouse = new boolean[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						isHouse[i][j] = true;
					}
				}
			}
			CNT = 0;
			operatingCosts = new int[N*2+1];
			settingOperatingCosts();
			
			simulation();
			sb.append("#"+tc+" "+CNT+"\n");
		}
		System.out.println(sb);
	}

	/**
	 * 필요한 k에 대해 미리 운영 비용을 계산하는 함수
	 * 어느 위치에서는 운영비용은 변하지 않고 k에만 의존하기 때문에 미리 구함
	 */
	private static void settingOperatingCosts() {
		for(int k=1; k<=N*2; k++) {
			operatingCosts[k] = k*k + (k-1)*(k-1);
		}
	}
	
	/**
	 * 각 행, 열에서 가능한 k에 따라 서비스 영역, 집 개수를 계산 및 갱신 
	 */
	private static void simulation() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int k_size = Math.max(N-i, Math.max(N-j, Math.max(i+1, j+1))); 
				int total_house = 0;
				for(int k=1; k<=k_size*2; k++) {
					total_house += getHouseCount(i, j, k);
					if(total_house <= CNT) continue;
					if(operatingCosts[k] <= total_house*M) {
						CNT = total_house;
					}
				}
			}
		}
	}
	
	/**
	 * k값에 따른 외곽선에 위치하는 집의 개수만 세는 함수
	 * 만약 k=1이면 현 위치의 집 개수를 반환
	 * 
	 * @param r : 현재 행 위치
	 * @param c : 현재 열 위치
	 * @param k : 현재 k값
	 * @return
	 */
	private static int getHouseCount(int r, int c, int k) {
		int cnt = 0;
		int dia = -1; // 마름모를 위 아래로 나눌 때 외곽선의 떨어진 거리 (마름모의 양 끝 행은 0, 마름모 중앙 행은 k-1) 
		for(int i= r-k+1; i<r+k; i++) { // 마름모의 맨 위의 행 부터 시작
			if(i <= r) dia++; 
			else if(i > r) dia--;
			if(i < 0 || i >= N) continue; // 행이 벗어났을 경우 건너뛰기
			// 외곽선에 위치하는 맨왼쪽과 맨오른쪽열 계산
			int left = c-dia;
			int right = c+dia;
			if(left == right) { // 두 열이 같다면 한번만 집을 세기 위해 확인
				if(left >= 0 && isHouse[i][left]) cnt++;
			} else { // 두 열이 다르다면 왼쪽, 오른쪽 각각 집 세기
				if(left >= 0 && isHouse[i][left]) cnt++;
				if(right < N  && isHouse[i][right]) cnt++;
			}
		}
		return cnt;
	}
}
