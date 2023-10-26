package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Stack;

public class boj_1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        List<Character> list = new LinkedList<Character>();
        for(int i=0; i<word.length; i++) {
            list.add(word[i]);
        }

        int N = Integer.valueOf(br.readLine());
        ListIterator<Character> cursor = list.listIterator();
        while(cursor.hasNext()){
            cursor.next();
        }
        while(N-- > 0) {
            String[] orders = br.readLine().split(" ");
            switch (orders[0]) {
                case "L":
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                    }
                    break;
                case "D":
                    if (cursor.hasNext()) {
                        cursor.next();
                    }
                    break;
                case "B":
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                    break;
                case "P":
                    cursor.add(orders[1].charAt(0));
                    break;

            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character chr: list){
            sb.append(chr);
        }
        System.out.println(sb);

    }
}
