package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


class Top {
    int idx;
    int height;
    public Top(int idx, int height) {
        this.idx = idx;
        this.height = height;
    }
}

public class boj_2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Top> stack = new Stack<Top>();
        StringBuilder sb = new StringBuilder();

        String[] line = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            int height = Integer.parseInt(line[i - 1]);

            if (stack.isEmpty()) {
                sb.append("0 ");
                stack.push(new Top(i, height));
                continue;
            }
            while (true) {
                if (stack.isEmpty()) {
                    sb.append("0 ");
                    stack.push(new Top(i, height));
                    break;
                }

                Top top = stack.peek();
                if (top.height > height) {
                    sb.append(top.idx + " ");
                    stack.push(new Top(i, height));
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        System.out.println(sb);
    }
}