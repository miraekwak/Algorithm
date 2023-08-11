package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1929_소수구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        int M = Integer.valueOf(numbers[0]);
        int N = Integer.valueOf(numbers[1]);
        boolean[] prime = new boolean[N+1];
        prime[0] = true;
        prime[1] = true;
        for(int i=2; i<=Math.sqrt(prime.length-1); i++) {
            if(!prime[i]){
                for(int j=i*i; j<prime.length; j=j+i){
                    prime[j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=M; i<prime.length; i++) {
            if (!prime[i]) {
                sb.append(i).append('\n');
            }
        }
        System.out.print(sb);
    }
}

