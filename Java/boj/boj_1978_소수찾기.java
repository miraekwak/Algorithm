package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1978_소수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        String[] numbers = br.readLine().split(" ");
        boolean[] prime = new boolean[1001];
        prime[0] = true;
        prime[1] = true;
        for(int i=2; i<=Math.sqrt(prime.length-1); i++) {
            if(!prime[i]){
                for(int j=i*i; j<prime.length; j=j+i){
                    prime[j] = true;
                }
            }
        }
        int cnt = 0;
        for(String num: numbers) {
            if (!prime[Integer.valueOf(num)]) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
