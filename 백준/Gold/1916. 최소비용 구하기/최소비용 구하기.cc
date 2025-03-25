#include <iostream>
#include <vector>
#include <queue>
#define INF 1e8
using namespace std;

int N, M, start, dest;	// 1 ≤ N ≤ 1,000, 1 ≤ M ≤ 100,000
vector<pair<int, int>> adj[1001];
int dist[1001];

void dijkstra(int s)
{
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
	fill(dist, dist + N + 1, INF);

	dist[s] = 0;
	pq.push({0, s});

	while(!pq.empty())
	{
		auto [cost, cur] = pq.top();
		pq.pop();
		if(dist[cur] < cost)	continue;

		for(auto [next, weight] : adj[cur]){
			if(dist[next] > cost + weight){
				dist[next] = cost + weight;
				pq.push({dist[next], next});
			}
		}
	}
	
}
int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;

	for(int i = 0, a, b, c; i < M; i++){
		cin >> a >> b >> c;
		adj[a].push_back({b, c});
	}

	cin >> start >> dest;

	dijkstra(start);
	cout << dist[dest] << '\n';

	return 0;
}