package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Stack{
    ArrayList<Integer> list;
    int point;
    public Stack(){
        this.list = new ArrayList<Integer>();
        this.point = 0;
    }

    public void push(int num){
        this.list.add(num);
        this.point++;
    }

    public int pop(){
        if(point == 0){
            return -1;
        }
        this.point--;
        return this.list.remove(point);
    }

    public int size(){
        return this.point;
    }

    public int empty() {
        return point > 0 ? 0 : 1;
    }

    public int top() {
        if(point == 0) {
            return -1;
        }
        return list.get(point-1);
    }


}

public class boj_10828_스택{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.valueOf(bufferedReader.readLine());
        Stack stack = new Stack();
        for(int i=0; i < num; i++){
            String[] orders = bufferedReader.readLine().split(" ");
            String order = orders[0];
            switch (order) {
                case "push":
                    stack.push(Integer.valueOf(orders[1]));
                    break;
                case "pop":
                    System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(stack.empty());
                    break;
                case "top":
                    System.out.println(stack.top());
                    break;
                default:
                    break;
            }
        }


    }
}