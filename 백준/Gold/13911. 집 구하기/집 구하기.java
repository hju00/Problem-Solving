import java.io.*;
import java.util.*;

class Main {
    
    static final int INF = Integer.MAX_VALUE;

    // 정점의 개수 V(3 ≤ V ≤ 10,000)와 도로의 개수 E(0 ≤ E ≤ 300,000)
    static int V, E;
    
    // 맥세권일 조건 x(1 ≤ x ≤ 100,000,000), 스세권일 조건 y(1 ≤ y ≤ 100,000,000)
    static int x, y;

    // M개의 맥도날드 정점 번호, S개의 스타벅스 정점 번호
    static int M, S;
    static HashSet<Integer> mSet;
    static HashSet<Integer> sSet;

    // 거리 저장 배열
    static int[] mDist;
    static int[] sDist;

    // 간선 정보 저장 인접리스트
    static ArrayList<Road> roads[];

    static class Road implements Comparable<Road>{
        int from;
        int to;
        int cost;

        public Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Road o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        roads = new ArrayList[V + 1];

        mSet = new HashSet<>();
        sSet = new HashSet<>();

        for(int i = 0; i <= V; i++)
            roads[i] = new ArrayList<>();
        
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Road r1 = new Road(from, to, cost);
            Road r2 = new Road(to, from, cost);

            roads[from].add(r1);
            roads[to].add(r2);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());
            mSet.add(temp);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sSet.add(temp);
        }
    }

    static int calculate() {

        int ret = INF;

        dijkstra1();
        dijkstra2();

        for(int i = 1; i <= V; i++) {
            if(mSet.contains(i) || sSet.contains(i))
                continue;

            if(mDist[i] == INF || sDist[i] == INF)
                continue;

            if(mDist[i] > x || sDist[i] > y)
                continue;
                
            ret = Math.min(ret, mDist[i] + sDist[i]);
        }

        if(ret == INF)
            return -1;
        return ret;
    }

    static void dijkstra1() {
        mDist = new int[V + 1];
        Arrays.fill(mDist, INF);

        PriorityQueue<Road> pq = new PriorityQueue<>();
        for(int i : mSet) {
            mDist[i] = 0;
            pq.add(new Road(i, i, 0));
        }

        while(!pq.isEmpty()) {
            Road c = pq.poll();

            if(mDist[c.to] < c.cost)
                continue;

            for(Road nxr : roads[c.to]) {
                int newCost = mDist[nxr.from] + nxr.cost;

                if(mDist[nxr.to] > newCost) {
                    mDist[nxr.to] = newCost;
                    pq.add(new Road(nxr.from, nxr.to, newCost));
                }
            }
        }
    }

    static void dijkstra2() {
        sDist = new int[V + 1];
        Arrays.fill(sDist, INF);

        PriorityQueue<Road> pq = new PriorityQueue<>();
        for(int i : sSet) {
            sDist[i] = 0;
            pq.add(new Road(i, i, 0));
        }

        while(!pq.isEmpty()) {
            Road c = pq.poll();

            if(sDist[c.to] < c.cost)
                continue;

            for(Road nxr : roads[c.to]) {
                int newCost = sDist[nxr.from] + nxr.cost;

                if(sDist[nxr.to] > newCost) {
                    sDist[nxr.to] = newCost;
                    pq.add(new Road(nxr.from, nxr.to, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calculate());
    }

}