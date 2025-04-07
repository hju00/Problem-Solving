#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	
	int n;	// 1 ≤ n ≤ 50,000
	cin >> n;
	int dp[50001];

	dp[0] = 0;

	for(int i = 1; i <= n; i++) {
		dp[i] = i;

		for(int j = 1; j * j <= i; j++) {
			dp[i] = min(dp[i], dp[i - (j * j)] + 1);
		}
	}

	cout << dp[n] << '\n';
	
	return 0;
}