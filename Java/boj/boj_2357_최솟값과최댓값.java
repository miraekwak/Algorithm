package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2357_최솟값과최댓값 {
    public static long[] arr;
    public static long[] tree_min;
    public static long[] tree_max;
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
        tree_max = new long[size];
        init_min(1, N, 1);
        init_max(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.valueOf(line[0]);
            int b = Integer.valueOf(line[1]);
            sb.append(search(1, N, 1, a, b, true) + " "
                    + search(1, N, 1, a, b, false) + "\n");
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
    private static long init_max(int start, int end, int node) {
        if(start == end) {
            return tree_max[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree_max[node] = Math.max(init_max(start, mid, node*2), init_max(mid+1, end, node*2+1));
    }
    private static long search(int start, int end, int node, int left, int right, boolean isMin) {
        if(end < left || start > right) {
            return isMin? Long.MAX_VALUE : 0;
        }
        if(left<=start && end <= right) {
            return isMin ? tree_min[node] : tree_max[node];
        }
        int mid = (start + end) / 2;
        long a = search(start, mid, node*2, left, right, isMin);
        long b = search(mid+1, end, node*2+1, left, right, isMin);
        return isMin ? Math.min(a, b) : Math.max(a, b);
    }
}
