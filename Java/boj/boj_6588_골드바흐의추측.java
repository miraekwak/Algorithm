package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_6588_골드바흐의추측 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean[] prime = new boolean[1000001];
        prime[0] = true;
        prime[1] = true;
        for(int i=0; i<=Math.sqrt(prime.length-1); i++) {
            if(!prime[i]) {
                for(int j=i*i; j<prime.length; j+=i) {
                    prime[j] = true;
                }
            }
        }
        int n;
        while((n = Integer.valueOf(bufferedReader.readLine())) != 0) {
            boolean ok = false;
            for(int i=2; i<= n/2; i++) {
                if(!prime[i] && !prime[n-i]) {
                    System.out.println(n + " = " + i + " + " + (n-i));
                    ok = true;
                    break;
                }
            }
            if(!ok) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}
