package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class boj_1260_DFS와BFS {
    private static int N, M, V;
    private static boolean[][] edgeMap;
    private static boolean[] visited;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] order = br.readLine().split(" ");
        N = Integer.parseInt(order[0]);
        M = Integer.parseInt(order[1]);
        V = Integer.parseInt(order[2]);
        edgeMap = new boolean[N+1][N+1];

        for(int i=0; i<M; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]), b = Integer.parseInt(line[1]);
            edgeMap[a][b] = true;
            edgeMap[b][a] = true;
        }

        sb = new StringBuilder();
        visited = new boolean[N+1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N+1];
        bfs(V);
        System.out.println(sb);
    }

    private static void dfs(int std) {
        visited[std] = true;
        sb.append(std+" ");
        for(int i=1; i<=N ; i++) {
            if(edgeMap[std][i] && !visited[i]) {
                dfs(i);
            }
        }
    }

    private static void bfs(int std) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(std);
        visited[std] = true;
        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            sb.append(vertex + " ");
            for (int i = 1; i <= N; i++) {
                if (edgeMap[vertex][i] && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
