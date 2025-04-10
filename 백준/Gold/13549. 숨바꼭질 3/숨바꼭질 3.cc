#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

// 🔥 핵심 포인트
// 	•	가중치가 다른 경로가 존재함 → 일반적인 BFS (큐 사용)으로는 최단 경로가 보장되지 않음.
// 	•	그래서 우선순위 큐(Dijkstra) 혹은 0-1 BFS (deque) 를 사용해야 함!

// 💡 0-1 BFS란?
// 	•	가중치가 0인 간선은 덱의 앞에, 가중치가 1인 간선은 덱의 뒤에 넣는 방식의 BFS.

bool isValid(int n)	{
	return (n >= 0) && (n <= 100000);
}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, K;	// 0 ≤ N ≤ 100,000, 0 ≤ K ≤ 100,000
	cin >> N >> K;

	vector<int> dp(100001, -1);
	dp[N] = 0;

	deque<int> dq;
	dq.push_back(N);

	while(!dq.empty())
	{
		int c = dq.front();
		dq.pop_front();

		if(c == K)	break;

		// 순간이동: 0초
		if (isValid(c * 2) && dp[c * 2] == -1) {
			dp[c * 2] = dp[c];
			dq.push_front(c * 2);
		}

		// 걷기: 1초
		if (isValid(c - 1) && dp[c - 1] == -1) {
			dp[c - 1] = dp[c] + 1;
			dq.push_back(c - 1);
		}
		if (isValid(c + 1) && dp[c + 1] == -1) {
			dp[c + 1] = dp[c] + 1;
			dq.push_back(c + 1);
		}

	}

	cout << dp[K] << '\n';
	return 0;
}