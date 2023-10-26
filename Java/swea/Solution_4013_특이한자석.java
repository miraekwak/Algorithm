package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간]
 * 시작 : 1:00
 * 종료 : 2:00
 * 
 * 문제]
 * 4개의 자석, 8개 날
 * 각 날마다 N또는 S극 자성
 * 하나의 자석이 1칸 회전 시 붙어있는 자석은 서로 붙어있는 날의 자석과 다를 경우에만 반대방향으로 1칸 회전
 * 1번 N극 0점, S극 1점
 * 2번 N극 0점, S극 2점
 * 3번 N극 0점, S극 4점
 * 4번 N극 0점, S극 8점
 * 
 * k번 회전시 획득한 점수의 총 합
 * 
 * 
 * 입력]
 * T 테스트 케이스 개수
 * K 자석을 회전시키는 횟수
 * 4개 자석의 자성정보(N:0, S:1) -> 빨간색 위치부터 시계방향
 * 좌석 번호 회전 방향(1:시계방향, -1:반시계방향) 
 * 
 * 
 * 알고리즘] 
 * 4개의 자성 정보 자료구조
 * - 8개 bit로 표시하자
 * - 회전한 이후 bit열에 대해 shift 연산으로 구할 수 있음
 * 
 * 회전 여부
 * - int값 일차원 배열로 각 자석의 회전 여부 표시
 * - 1, 0, -1
 * 
 * 회전 여부 계산
 * - 시작 위치에서 왼쪽 오른쪽으로 전이
 * - 비트열에서 2번, 6번 인덱스가 
 * 
 *  
 * 시간복잡도]
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_4013_특이한자석 {

	static int K, magnet[], rotation[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		magnet = new int[4];
		rotation = new int[4];
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=TC; tc++) {
			K = Integer.parseInt(br.readLine());
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				int num = 0;
				for(int j=0; j<8; j++) {
					int ns = Integer.parseInt(st.nextToken());
					if(ns == 1) num |= (1<<j);
				}
				magnet[i] = num;
			}
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken()) -1;
				int r = Integer.parseInt(st.nextToken());
				calculateRotation(m, r);
				rotate();
			}
			sb.append("#"+tc+" "+score()+"\n");
		}
		System.out.println(sb);
	}

	private static int score() {
		int score = 0;
		for(int i=0, s=1; i<4; i++, s*=2) {
			if((magnet[i] & 1) > 0) score += s;
		}
		return score;
	}

	private static void calculateRotation(int num, int r) {
		rotation[num] = r;
		// 기준 오른쪽
		for(int i=num+1; i<4; i++) {
			int left = (magnet[i-1] & (1<<2)) > 0 ? 1 : 0;
			int right = (magnet[i] & (1<<6)) > 0 ? 1 : 0;
			if(left == right) break;
			rotation[i] = rotation[i-1] * -1;
		}
		// 기준 왼쪽
		for(int i=num-1; i>=0; i--) {
			int left = (magnet[i] & (1<<2)) > 0 ? 1 : 0;
			int right = (magnet[i+1] & (1<<6)) > 0 ? 1 : 0;
			if(left == right) break;
			rotation[i] = rotation[i+1] * -1;
		}
	}

	private static void rotate() {
		for(int i=0; i<4; i++) {
			if(rotation[i] == 0) continue;
			if(rotation[i] == -1) {
				int num = magnet[i];
				int first = num & 1;
				magnet[i] = (num >> 1) | (first<<7);
			}
			else {
				int num = magnet[i];
				int last = (num & (1<<7)) > 0 ? 1 : 0;
				num &= ~(1<<7); 
				magnet[i] = (num << 1) | last;
			}
			rotation[i] = 0;
		}
	}
}
