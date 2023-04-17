package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(int i=0; i<line.length(); i++) {
            if(line.charAt(i) == '(') {
                stack.push('(');
            }
            else{
                if(line.charAt(i-1) == ')') {
                    count++;
                }
                else if(stack.size() > 1) {
                    count += stack.size()-1;
                }
                stack.pop();
            }
        }
        System.out.println(count);
    }
}
