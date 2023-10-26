package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1918_후위표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] postfix = br.readLine().toCharArray();
        Stack<Character> operators = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<postfix.length; i++) {
            if('A' <= postfix[i] && postfix[i] <= 'Z') {
                sb.append(postfix[i]);
            }
            else if(postfix[i] == '(') {
                operators.push(postfix[i]);
            }
            else if(postfix[i] == ')') {
                while(!operators.empty() && operators.peek() != '(') {
                    sb.append(operators.pop());
                }
                operators.pop();
            }
            else {
                while(!operators.empty() && priority(operators.peek()) >= priority(postfix[i])) {
                    sb.append(operators.pop());
                }
                operators.push(postfix[i]);
            }

        }
        while(!operators.empty()) {
            sb.append(operators.pop());
        }
        System.out.println(sb);
    }
    static int priority(char c) {
        if( c == '(') {
            return 0;
        }
        else if( c == '+' || c == '-') {
            return 1;
        }
        else  {
            return 2;
        }
    }
}
