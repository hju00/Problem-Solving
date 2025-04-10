#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N;	// 1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000
	cin >> N;

	int A[1000];
	for(int i = 0; i < N; i++)	cin >> A[i];

	vector<int> dp_inc(N, 1);
	vector<int> dp_dec(N, 1);
	
	for(int i = 0; i < N; i++)	
		for(int j = 0; j < i; j++)	
			if(A[j] < A[i])	
				dp_inc[i] = max(dp_inc[i], dp_inc[j] + 1);

	for(int i = N - 1; i >= 0; i--)
		for(int j = N - 1; j > i; j--)
			if(A[j] < A[i])
				dp_dec[i] = max(dp_dec[i], dp_dec[j] + 1);

	int ans = 0;
	for(int i = 0; i < N; i++)
		ans = max(ans, dp_inc[i] + dp_dec[i] - 1);

	cout << ans << '\n';
	return 0;
}