package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class boj_9093_단어뒤집기{
    public static String backward(String s){
        String new_str="";
        for(int i=s.length()-1; i >= 0; i--){
            new_str = new_str + s.charAt(i);
        }
        return new_str;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(bf.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            while(st.hasMoreTokens()){
                sb.append(backward(st.nextToken())+" ");
                // StringBuilder.reverse() 사용가능
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}