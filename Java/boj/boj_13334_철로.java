import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제]
 *
 */
public class boj_13334_철로 {
    static int N, D;
    static ArrayList<Person> persons;

    static class Person implements Comparable<Person> {
        int sl;
        int ll;
        public Person(int sl, int ll) {
            this.sl = sl;
            this.ll = ll;
        }

        @Override
        public int compareTo(Person o) {
            return this.ll - o.ll;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        persons = new ArrayList<>();
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if(h < o) persons.add(new Person(h, o));
            else persons.add(new Person(o, h));
        }
        D = Integer.parseInt(br.readLine());
        Collections.sort(persons);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCnt = 0;
        int cnt = 0;
        for (Person p: persons) {
            while(!pq.isEmpty() && pq.peek() < p.ll - D) {
                pq.poll();
                cnt--;
            }

            if(p.sl >= p.ll - D) {
                cnt++;
                pq.add(p.sl);
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(maxCnt);
    }
}
