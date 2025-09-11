import java.io.*;
import java.util.*;

public class Main {

    static class Jewel implements Comparable<Jewel> {
        int w;
        int v;

        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }

        // 무게 기준 오름차순 정렬
        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) 
            bags[i] = Integer.parseInt(br.readLine());
        

        Arrays.sort(jewels);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;
        int jIdx = 0;

        // 가장 작은 가방부터 순회
        for (int i = 0; i < K; i++) {
            int bagCapacity = bags[i];

            // 현재 가방에 담을 수 있는 모든 보석을 후보 PQ에 추가
            while (jIdx < N && jewels[jIdx].w <= bagCapacity) {
                pq.add(jewels[jIdx].v);
                jIdx++;
            }

            // 후보 PQ가 비어있지 않다면, 가장 가치 높은 보석을 담음
            if (!pq.isEmpty())  ans += pq.poll();
        }

        System.out.println(ans);
    }
}