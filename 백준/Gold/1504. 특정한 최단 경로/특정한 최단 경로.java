import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int to, cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, E;
    static List<Node>[] graph;
    static final int INF = 200000000; // 충분히 큰 값

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = dijkstra(1);
        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        long path1 = (long)dist1[v1] + distV1[v2] + distV2[N]; // 1 → v1 → v2 → N
        long path2 = (long)dist1[v2] + distV2[v1] + distV1[N]; // 1 → v2 → v1 → N

        long ans = Math.min(path1, path2);
        System.out.println(ans >= INF ? -1 : ans);
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > dist[now.to]) continue;
            for (Node next : graph[now.to]) {
                if (dist[next.to] > dist[now.to] + next.cost) {
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }
}
