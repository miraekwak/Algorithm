import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제]
 * 기부할 수 있는 최대 랜선길이 구하기
 *
 * 풀이]
 * 최소신장트리를 찾아 [전체 간선길이 - 최소값 = 최대 랜선길이 ]
 */
public class boj_1414_불우이웃돕기 {
    static private int N, wireTotal;
    static private int[][] matrix;

    static class Vertex implements Comparable<Vertex>{
        int num, weight;
        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        wireTotal = 0;
        int standard;
        char c;
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                c = line.charAt(j);
                standard = 0;
                if(Character.isUpperCase(c)) standard = (c - 'A' ) + 27;
                else if(Character.isLowerCase(c)) standard = (c - 'a') + 1;
                matrix[i][j] = standard;
                wireTotal += standard;
            }
        }

        System.out.println(prime());
    }

    static private int prime() {
        boolean[] visited = new boolean[N];
        int[] minEdge = new int[N];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        minEdge[0] = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(new Vertex(0, 0));

        int total = 0, cnt = 0;
        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();
            if(visited[curr.num]) continue;

            total += curr.weight;
            minEdge[curr.num] = curr.weight;
            visited[curr.num] = true;
            if(++cnt >= N) return wireTotal - total;

            for(int to=0; to<N; to++) {
                if(!visited[to] && matrix[curr.num][to] != 0 && minEdge[to] > matrix[curr.num][to]) {
                    minEdge[to] = matrix[curr.num][to];
                    queue.offer(new Vertex(to, minEdge[to]));
                }
                if(!visited[to] && matrix[to][curr.num] != 0 && minEdge[to] > matrix[to][curr.num]) {
                    minEdge[to] = matrix[to][curr.num];
                    queue.offer(new Vertex(to, minEdge[to]));
                }
            }
        }

        return -1;
    }
}
