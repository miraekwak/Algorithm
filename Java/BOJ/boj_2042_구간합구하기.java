package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class boj_2042_구간합구하기 {
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] order = br.readLine().split(" ");
        int N = Integer.valueOf(order[0]);
        int M = Integer.valueOf(order[1]);
        int K = Integer.valueOf(order[2]);
        arr = new long[N+1];
        for(int i=1; i<=N; i++) {
            arr[i] = Long.valueOf(br.readLine());
        }
        int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, height);
        tree = new long[size];
        init(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.valueOf(line[0]);
            int b = Integer.valueOf(line[1]);
            long c = Long.valueOf(line[2]);
            if(a == 1) {
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            }
            else if(a == 2) {
                sb.append(sum(1, N, 1, b, (int)c)+"\n");
            }
        }
        System.out.println(sb);
    }

    public static long init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if(left > end || right < start) {
            return 0;
        }
        if(left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    public static void update(int start, int end, int node, int index, long dif) {
        if(start > index || end < index) {
            return;
        }
        tree[node] += dif;
        if(start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node*2, index, dif);
        update(mid+1, end, node*2+1, index, dif);
    }
}
