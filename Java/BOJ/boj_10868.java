package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_10868 {
    public static long[] arr;
    public static long[] tree_min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] order = br.readLine().split(" ");
        int N = Integer.valueOf(order[0]);
        int M = Integer.valueOf(order[1]);
        arr = new long[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }
        int height = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int) Math.pow(2, height);
        tree_min = new long[size];
        init_min(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.valueOf(line[0]);
            int b = Integer.valueOf(line[1]);
            sb.append(search(1, N, 1, a, b) +"\n");
        }
        System.out.println(sb);
    }
    private static long init_min(int start, int end, int node) {
        if(start == end) {
            return tree_min[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree_min[node] = Math.min(init_min(start, mid, node*2), init_min(mid+1, end, node*2+1));
    }

    private static long search(int start, int end, int node, int left, int right) {
        if(end < left || start > right) {
            return Long.MAX_VALUE;
        }
        if(left<=start && end <= right) {
            return tree_min[node];
        }
        int mid = (start + end) / 2;
        long a = search(start, mid, node*2, left, right);
        long b = search(mid+1, end, node*2+1, left, right);
        return Math.min(a, b);
    }
}
