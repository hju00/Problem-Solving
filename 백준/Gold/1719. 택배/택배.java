import java.io.*;
import java.util.*;

class Main {

    static final int INF = 999_999_999;
    // n은 집하장의 개수로 200이하의 자연수, m은 집하장간 경로의 개수로 10000이하의 자연수
    static int n, m; 
    static int ans[][];
    static int dist[];
    static ArrayList<Edge> edges[];

    static class Edge implements Comparable<Edge> {
        int start;
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public Edge(int start, int to, int cost) {
            this.start = start;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return Integer.compare(this.cost, o.cost);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new int[n + 1][n + 1];
        edges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            edges[from].add(new Edge(to, cost));
            edges[to].add(new Edge(from, cost));
        }

        for(int i = 1; i <= n; i++) 
            dijkstra(i);
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(ans[i][j] == 0)  System.out.print("- ");
                else                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void dijkstra(int s) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        ans[s][s] = 0;

        pq.add(new Edge(s, 0));

        while(!pq.isEmpty()) {
            Edge c = pq.poll();
            
            if(c.cost > dist[c.to])     continue;

            for(Edge n : edges[c.to]) {
                int newCost = dist[c.to] + n.cost;

                if(newCost < dist[n.to]) {
                    dist[n.to] = newCost;
                    if(c.cost == 0) {
                        pq.add(new Edge(n.to, n.to, newCost));
                        ans[s][n.to] = n.to;
                    }
                    else {
                        pq.add(new Edge(c.start, n.to, newCost));  
                        ans[s][n.to] = c.start;
                    }                  
                }
            }
        }

    }
}