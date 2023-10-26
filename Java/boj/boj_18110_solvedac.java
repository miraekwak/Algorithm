package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_18110_solvedac {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		for(int i=0; i<n; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(numbers);
		double total = 0;
		int except = (int) Math.round(n*0.15);
		for(int i=except; i<n-except; i++) {
			total += numbers[i];
		}
		System.out.println(Math.round(total/(n-except*2)));
	}
}
