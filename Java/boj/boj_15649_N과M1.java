package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_15649_N과M1 {
    private static int N, M;
    private static boolean[] visited;
    private static int[] numbers;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        visited = new boolean[N];
        numbers = new int[M];
        sb = new StringBuilder();
        permu(0);
        System.out.println(sb);
    }

    private static void permu(int cnt) {
        if(cnt == M) {
            for(int i=0; i<M; i++) {
                sb.append(numbers[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            numbers[cnt] = i+1;
            permu(cnt+1);
            visited[i] = false;
        }
    }

}
