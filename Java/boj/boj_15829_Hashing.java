package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15829_Hashing {

	public static void main(String[] args) throws IOException {

		int r = 31;
		int m = 1234567891;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine());
		String word = br.readLine();
		long value = 0;
		long pow = 1;
		for(int i=0; i<word.length(); i++) {
			value += pow * (word.charAt(i) -'a' + 1);
			pow = (pow*31) % m;
		}
		System.out.println(value % m);
	}
}
