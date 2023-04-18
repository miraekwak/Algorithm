package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        char[] line = br.readLine().toCharArray();
        double[] number = new double[N];
        for(int i=0; i<N; i++) {
            number[i] = Double.valueOf(br.readLine());
        }
        Stack<Double> operands = new Stack<>();
        for(int i=0; i<line.length; i++) {
            if('A' <= line[i] && line[i] <= 'Z') {
                operands.push(number[line[i]-'A']);
            }
            else {
                double num2 = operands.pop();
                double num1 = operands.pop();
                switch (line[i]) {
                    case '+':
                        operands.push(num1 + num2);
                        break;
                    case '-':
                        operands.push(num1 - num2);
                        break;
                    case '*':
                        operands.push(num1 * num2);
                        break;
                    case '/':
                        operands.push(num1 / num2);
                        break;
                }
            }
        }
        System.out.printf("%.2f", operands.pop());
    }
}
