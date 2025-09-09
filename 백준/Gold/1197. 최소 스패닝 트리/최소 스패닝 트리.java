import java.io.*;
import java.util.*;

class Main {

    // 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)
    static int V, E;

    static int parent[];

    static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Node o) {
            return this.cost - o.cost;
        }
        
    }
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for(int i = 0; i <= V; i++)
            parent[i] = i;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, cost));
        }

        int cnt = 0;
        int result = 0;
        while(!pq.isEmpty() || cnt < V - 1) {
            Node c = pq.poll();
            if(union(c.from, c.to)) {
                result += c.cost;
                cnt++;
            }
        }

        System.out.println(result);
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY)  return false;
        parent[rootY] = rootX;
        return true;
    }

    static int find(int x) {
        if(x == parent[x])  return x;
        return parent[x] = find(parent[x]);
    }
}