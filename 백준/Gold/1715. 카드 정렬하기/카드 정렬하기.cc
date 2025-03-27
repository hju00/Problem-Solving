#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N;	// 1 ≤ N ≤ 100,000

int main() {
	
	cin >> N;
	priority_queue<int, vector<int>, greater<>> pq;
	
	for(int temp, i = 0; i < N; i++){
		cin >> temp;
		pq.push(temp);
	}

	if(N <= 1) {
		cout << "0\n";
		return 0;
	}
	else {
		int ans = 0;

		while(pq.size() > 1)
		{
			int n1 = pq.top();
			pq.pop();
			int n2 = pq.top();
			pq.pop();
			
			ans += n1 + n2;
			pq.push(n1 + n2);
		}

		cout << ans << '\n';
		return 0;
	}

	return 0;
}