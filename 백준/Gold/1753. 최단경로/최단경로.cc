#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

const int INF = 1e8;
int V, E, K;	// 1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000

vector<int> dijkstra(int st, vector<pair<int, int>> g[20001])
{
	vector<int> dist(V + 1, INF);
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<> > pq;
	dist[st] = 0;
	pq.push({0, st});

	while(!pq.empty())
	{
		int cost = pq.top().first;
		int now = pq.top().second;
		pq.pop();

		if(dist[now] < cost)	continue;

		for(auto [next, next_cost] : g[now])	{
			int total_cost = cost + next_cost;

			if(dist[next] > total_cost)	{
				dist[next] = total_cost;
				pq.push({total_cost, next});
			}
		}
	}

	return dist;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> V >> E >> K;
	vector<pair<int, int>> graph[20001];

	while(E--)	{
		int u, v, w;
		cin >> u >> v >> w;
		graph[u].push_back({v, w});
	}

	vector<int> ans = dijkstra(K, graph);
	for(int i = 1; i <= V; i++) {
		if(ans[i] == INF)	cout << "INF\n";
		else	cout << ans[i] << '\n';
	}
	return 0;
}