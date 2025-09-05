import java.util.*;

class UserSolution {
	
	static int n;		// N: 도시의 개수 ( 5 ≤ N ≤ 1,000 )
	static int k;		// K: 도로의 개수 ( 2 ≤ K ≤ 2,000 )
	
	static ArrayList<Road> roads[];
	static int dist[];
	
	static void dijkstra(int s) {
		PriorityQueue<Road> pq = new PriorityQueue<>();
		
		// dist 배열을 -1로 초기화
		Arrays.fill(dist, -1);
		
		// dist 배열의 시작점을 최댓값으로 초기화
		// 왜?? 기존의 최소 코스트가 아닌 최대 코스트로 다익스트라를 실행하기 때문
		dist[s] = Integer.MAX_VALUE;
		pq.add(new Road(s, s, Integer.MAX_VALUE));
		
		while(!pq.isEmpty()) {
			Road c = pq.poll();
			
			// 현재 중량보다 이미 더 큰 중량으로 갈 수 있는 경우 pass
			if(dist[c.to] > c.cost)
				continue;
			
			// 현재 도시에서 갈 수 있는 모든 길
			for(Road nxr : roads[c.to]) {
				// 지금까지 왔던 중량과 다음 중량 중 작은 값을 사용해야함
				int newCost = Math.min(dist[c.to], nxr.cost);
				
				// 새로운 중량이 더 나으면 갱신
				if(newCost > dist[nxr.to]) {
					pq.add(nxr);
					dist[nxr.to] = newCost;			
				}
			}
		}
		
	}
	
	static class Road implements Comparable<Road>{
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
			// 최대 중량을 반환해야 하기 때문에 내림차순 정렬
			return Integer.compare(o.cost, this.cost);
		}
	}
	
	public void init(int N, int K, int sCity[], int eCity[], int mLimit[]) {
		n = N;
		k = K;
		
		dist = new int[N];
		roads = new ArrayList[N];
		for(int i = 0; i < N; i++)
			roads[i] = new ArrayList<>();
		
		for(int i = 0; i < K; i++)	{
			int from = sCity[i];
			int to = eCity[i];
			int cost = mLimit[i];
			
			Road r1 = new Road(from, to, cost);
			roads[from].add(r1);
			
			Road r2 = new Road(to, from, cost);
			roads[to].add(r2);
		}
		
		return;
	}

	public void add(int sCity, int eCity, int mLimit) {
		
		Road r1 = new Road(sCity, eCity, mLimit);
		roads[sCity].add(r1);
		
		Road r2 = new Road(eCity, sCity, mLimit);
		roads[eCity].add(r2);
		
		return;
	}

	public int calculate(int sCity, int eCity, int M, int mStopover[]) {

		// 다익스트라 실행 O(N log(k + add호출횟수)) = 1,000 * log(3,200) = 1,000 * 3.5 = 350
		dijkstra(sCity);
		
		// 출발점에서 각 도시까지 갈 수 있는 최대 중량이 dist에 저장되어 있음
		// 갈 수 없는 도시는 dist를 -1로 초기화 해주었기 때문에 경유지 + 목적지 중 가장 작은 중량을 출력
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) 
			pq.add(dist[mStopover[i]]);
		
		pq.add(dist[eCity]);
		
		return pq.poll();
	}
}
