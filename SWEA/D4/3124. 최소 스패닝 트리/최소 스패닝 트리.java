
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int V, E;    // 정점의 개수 V(1≤V≤100,000)와 간선의 개수 E(1≤E≤200,000)
    static List<Edge> edgeList;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int from, to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edgeList = new ArrayList<>();
            parent = new int[V + 1];
            for(int i = 1; i <= V; i++)  parent[i] = i;

            for(int i = 0; i < E; i++)  {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                edgeList.add(new Edge(a, b, c));
            }

            Collections.sort(edgeList);

            long result = 0;
            int edgeCnt = 0;
            for(Edge edge : edgeList)   {
                if(union(edge.from, edge.to))   {
                    result += edge.cost;
                    edgeCnt++;
                }

                if(edgeCnt == V - 1)    break;
            }

            System.out.println("#" + test_case + " " + result);
        }
    }

    static boolean union(int x, int y)  {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY)  return false;
        
        parent[rootY] = rootX;
        return true;
    }

    static int find(int x)  {
        if(parent[x] == x)  return x;
        return parent[x] = find(parent[x]);
    }
}
