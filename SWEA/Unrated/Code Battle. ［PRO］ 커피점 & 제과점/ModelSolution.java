import java.util.*;

class UserSolution {
    static ArrayList<int[]>[] graph;
    static int gN;

    static class Node implements Comparable<Node> {
        int dist;
        int id;
        int cb; // 0 = coffee, 1 = bakery

        public Node(int dist, int id, int cb) {
            this.dist = dist;
            this.id = id;
            this.cb = cb;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public void init(int N, int K, int[] sBuilding, int[] eBuilding, int[] mDistance) {
        gN = N;
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            graph[sBuilding[i]].add(new int[]{eBuilding[i], mDistance[i]});
            graph[eBuilding[i]].add(new int[]{sBuilding[i], mDistance[i]});
        }
    }

    public void add(int sBuilding, int eBuilding, int mDistance) {
        graph[sBuilding].add(new int[]{eBuilding, mDistance});
        graph[eBuilding].add(new int[]{sBuilding, mDistance});
    }

    public int calculate(int M, int[] mCoffee, int P, int[] mBakery, int R) {
        int ans = 987654321;
        PriorityQueue<Node> hq = new PriorityQueue<>();
        int[][] visit = new int[2][gN + 1];
        for (int i = 0; i < 2; i++) Arrays.fill(visit[i], -1);

        for (int i = 0; i < M; i++)
            hq.add(new Node(0, mCoffee[i], 0));

        for (int i = 0; i < P; i++)
            hq.add(new Node(0, mBakery[i], 1));

        while (!hq.isEmpty()) {
            Node cur = hq.poll();
            int dist = cur.dist;
            int tId = cur.id;
            int cb = cur.cb;

            if (visit[cb][tId] != -1) continue;
            visit[cb][tId] = dist;

            if (visit[0][tId] > 0 && visit[1][tId] > 0) {
                ans = Math.min(ans, visit[0][tId] + visit[1][tId]);
                continue;
            }

            for (int[] nxt : graph[tId]) {
                int nxtId = nxt[0];
                int nxtDist = dist + nxt[1];

                if (nxtDist > R || nxtDist > ans || visit[cb][nxtId] != -1) continue;
                hq.add(new Node(nxtDist, nxtId, cb));
            }
        }

        return ans == 987654321 ? -1 : ans;
    }
}
