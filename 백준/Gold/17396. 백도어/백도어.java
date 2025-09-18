import java.io.*;
import java.util.*;

class Main {

    static final long INF = Long.MAX_VALUE;

    static int N, M;    // (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000)
    static int a[];
    static ArrayList<Edge> edges[];
    static long dist[];

    static class Edge implements Comparable<Edge>{
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N];
        edges = new ArrayList[N];
        dist = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
            a[i] = Integer.parseInt(st.nextToken());
        }
        a[N - 1] = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, t));
            edges[b].add(new Edge(a, t));
        }

        dijkstra();

        if(dist[N - 1] == INF)
            System.out.println(-1);
        else
            System.out.println(dist[N - 1]);

    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);

        dist[0] = 0;
        pq.add(new Edge(0, 0));

        while(!pq.isEmpty()) {
            Edge c = pq.poll();

            if(c.cost > dist[c.to])     continue;

            for(Edge n : edges[c.to]) {
                if(a[n.to] == 1)    continue;

                long newCost = dist[c.to] + n.cost;
                if(newCost < dist[n.to]) {
                    dist[n.to] = newCost;
                    pq.add(new Edge(n.to, newCost));
                }
            }
        }
    }
}