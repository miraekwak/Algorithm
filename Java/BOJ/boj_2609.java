package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int num1 = Integer.valueOf(line[0]);
        int num2 = Integer.valueOf(line[1]);
        int a = num1 >= num2 ? num1 : num2;
        int b = num1 >= num2 ? num2 : num1;
        while(a%b != 0){
            int c = a%b;
            a = b;
            b = c;
        }
        System.out.println(b);
        System.out.println(num1*num2/b);
    }
}
