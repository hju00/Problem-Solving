import java.util.*;

class UserSolution {
	
	static final int INF = Integer.MAX_VALUE;
	
	static int n, k;
	
	static int[] from_coffee_dist;
	static int[] from_bakery_dist;
	
	static HashSet<Integer> coffeeID;
	static HashSet<Integer> bakeryID;
	
	static ArrayList<Road> roads[];
	
	static class Road implements Comparable<Road> {
		int from;
		int to;
		int cost;
		public Road(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Road o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {
		n = N;
		k = K;
		
		roads = new ArrayList[N];
		for(int i = 0; i < N; i++)
			roads[i] = new ArrayList<>();
		
		from_coffee_dist = new int[N];
		from_bakery_dist = new int[N];
		
		for(int i = 0; i < K; i++) {
			int from = sBuilding[i];
			int to = eBuilding[i];
			int cost = mDistance[i];
			
			Road r1 = new Road(from, to, cost);
			Road r2 = new Road(to, from, cost);
			
			roads[from].add(r1);
			roads[to].add(r2);
		}
		
		return;
	}

	public void add(int sBuilding, int eBuilding, int mDistance) {
		
		Road r1 = new Road(sBuilding, eBuilding, mDistance);
		Road r2 = new Road(eBuilding, sBuilding, mDistance);
		
		roads[sBuilding].add(r1);
		roads[eBuilding].add(r2);
		
		return;
	}

	public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {
		int ret = INF;
		
		coffeeID = new HashSet<>();
		bakeryID = new HashSet<>();
		
		for(int i = 0; i < M; i++)
			coffeeID.add(mCoffee[i]);
		
		for(int i = 0; i < P; i++)
			bakeryID.add(mBakery[i]);
		
		dijkstra1(R);
		dijkstra2(R);
		
		for(int i = 0; i < n; i++) {
			if(from_coffee_dist[i] == 0 || from_bakery_dist[i] == 0)
				continue;
			
			if(from_coffee_dist[i] == INF || from_bakery_dist[i] == INF)
				continue;
			
			ret = Math.min(ret, from_coffee_dist[i] + from_bakery_dist[i]);
		}
		
		if(ret == INF)
			return -1;
		return ret;
	}
	
	static void dijkstra1(int R) {
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		Arrays.fill(from_coffee_dist, INF);
		
		for(int i : coffeeID) {
			pq.add(new Road(i, i, 0));
			from_coffee_dist[i] = 0;
		}
		
		while(!pq.isEmpty()) {
			Road c = pq.poll();
			
			if(c.cost > from_coffee_dist[c.to])
				continue;
			
			for(Road n : roads[c.to]) {
				int newCost = from_coffee_dist[c.to] + n.cost;
				
				if(newCost <= R) 
					if(newCost < from_coffee_dist[n.to]) {
						pq.add(new Road(n.from, n.to, newCost));
						from_coffee_dist[n.to] = newCost;
					}
			}
		}
		
	}
	
	static void dijkstra2(int R) {
		PriorityQueue<Road> pq = new PriorityQueue<>();
		Arrays.fill(from_bakery_dist, INF);
		
		for(int i : bakeryID) {
			pq.add(new Road(i, i, 0));
			from_bakery_dist[i] = 0;
		}
		
		while(!pq.isEmpty()) {
			Road c = pq.poll();
			
			if(c.cost > from_bakery_dist[c.to])
				continue;
			
			for(Road n : roads[c.to]) {
				int newCost = from_bakery_dist[c.to] + n.cost;
				
				if(newCost <= R) 
					if(newCost < from_bakery_dist[n.to]) {
						pq.add(new Road(n.from, n.to, newCost));
						from_bakery_dist[n.to] = newCost;
					}
			}
		}
	}
}
