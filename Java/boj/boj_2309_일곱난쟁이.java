package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class boj_2309_일곱난쟁이 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = 9;
        int[] heights = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<9; i++) {
            int height = Integer.parseInt(bufferedReader.readLine());
            heights[i] = height;
            list.add(height);
        }
        int all_height = Arrays.stream(heights).sum();
        int except1 = 0;
        int except2 = 0;
        for(int i=0; i<9; i++) {
            for(int j=i+1; j<9; j++) {
                if(all_height-heights[i]-heights[j] == 100) {
                    except1 = i;
                    except2 = j;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        list.remove(except2);
        list.remove(except1);
        Collections.sort(list);
        for(int i=0; i<list.size(); i++) {
            sb.append(list.get(i)+"\n");
        }
        System.out.println(sb);
    }
}
