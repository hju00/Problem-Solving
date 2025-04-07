#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, K, A;	// 1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000
	cin >> N >> K;

	priority_queue<int, vector<int>, less<>> pq;

	while(N--) {
		cin >> A;
		pq.push(A);
	}

	int ans = 0;

	while(K > 0)
	{
		int coin = pq.top();
		pq.pop();

		if(coin > K)	continue;
		else	{
			ans += K / coin;
			K %= coin;
		}
	}

	cout << ans <<'\n';
	
	return 0;
}