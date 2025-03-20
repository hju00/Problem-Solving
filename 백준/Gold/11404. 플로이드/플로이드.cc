#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1e9;
int n, m;	
vector<vector<int>> dist;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m;
	dist.assign(n + 1, vector<int>(n + 1, INF));

	// 자기 자신으로 가는 경로는 0
	for(int i = 1; i <= n; i++) dist[i][i] = 0;

	for(int t = 0; t < m; t++) {
		int i, j, k;
		cin >> i >> j >> k;
		dist[i][j] = min(dist[i][j], k);  // 항상 더 작은 값 반영
	}

	// 플로이드-워셜 알고리즘
	for(int k = 1; k <= n; k++)
		for(int i = 1; i <= n; i++)
			for(int j = 1; j <= n; j++)
				if(dist[i][k] != INF && dist[k][j] != INF)  // 불필요한 연산 방지
					dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

	// 경로가 없는 경우 0을 출력
	for(int i = 1; i <= n; i++) {
		for(int j = 1; j <= n; j++)
			cout << (dist[i][j] == INF ? 0 : dist[i][j]) << ' ';
		cout << '\n';
	}
	
	return 0;
}
