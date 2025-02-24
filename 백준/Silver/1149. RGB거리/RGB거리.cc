#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N;	// (2 ≤ N ≤ 1,000)
	cin >> N;
	vector<vector<int>> cost(N, vector<int>(3, 0));
	vector<vector<int>> dp(N, vector<int>(3, 0));

	for(int i = 0; i < N; i++)
		cin >> cost[i][0] >> cost[i][1] >> cost[i][2];

	dp[0][0] = cost[0][0];
	dp[0][1] = cost[0][1];
	dp[0][2] = cost[0][2];

	for(int i = 1; i < N; i++)
	{
		dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
		dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
		dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
	}

	cout << min(dp[N-1][0], min(dp[N-1][1], dp[N-1][2])) << "\n";

	return 0;
}