package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(bf.readLine());
        while(N-- > 0) {
            char[] line = bf.readLine().toCharArray();
            Stack stack = new Stack();
            boolean pause = false;
            for(int i=0; i < line.length; i++){
                if(line[i] == '(') {
                    stack.push('(');
                }
                else if(line[i] == ')') {
                    if (stack.pop() == -1){
                        pause = true;
                        break;
                    }
                }
            }
            if(stack.empty() == 0 | pause){
                sb.append("NO\n");
            }else {
                sb.append("YES\n");
            }
        }
        System.out.println(sb);
    }
}
