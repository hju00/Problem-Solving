#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, x;	// 1 ≤ N ≤ 100,000
	cin >> N;
	
	priority_queue<int, vector<int>, less<>> pq;

	while(N--)
	{
		cin >> x;
		if(x == 0)	{
			if(pq.empty())	cout << "0\n";
			else {
				cout << pq.top() << '\n';
				pq.pop();
			}
		}
		else	pq.push(x);
	}

	return 0;
}