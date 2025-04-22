#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

const int INF = 1e9;
int N, M, X;	// 1 ≤ N ≤ 1,000, 1 ≤ M ≤ 10,000
vector<pair<int, int>> graph[1001];
vector<pair<int, int>> reversed_graph[1001];

vector<int> dijkstra(int st, vector<pair<int, int>> g[1001])	{
	vector<int> dist(N + 1, INF);
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
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

	cin >> N >> M >> X;

	for(int i = 1; i <= M; i++)	{
		int a, b, T;
		cin >> a >> b >> T;
		graph[a].push_back({b, T});
		reversed_graph[b].push_back({a, T});
	}

	//	X -> i (다녀오는 거리)
	vector<int> fromX = dijkstra(X, graph);
	// i -> X (가는 거리)
	vector<int> toX = dijkstra(X, reversed_graph);

	int max_time = 0;
	for(int i = 1; i <= N; i++)	
		max_time = max(max_time, toX[i] + fromX[i]);

	cout << max_time << '\n';
	return 0;
}