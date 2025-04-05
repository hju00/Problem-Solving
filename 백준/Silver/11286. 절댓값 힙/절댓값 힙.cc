#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;

struct cmp {
	bool operator()(int a, int b)	{
		if(abs(a) == abs(b))	return a > b;
		else	return abs(a) > abs(b);
	}
};

int main()
{
	int N, x;	// 1 ≤ N ≤ 100,000
	cin >> N;

	priority_queue<int, vector<int>, cmp> pq;

	while(N--)
	{
		cin >> x;
		if(x == 0)	{
			if(pq.empty())	cout << "0\n";
			else	{
				cout << pq.top() << '\n';
				pq.pop();
			}
		}
		else	{
			pq.push(x);
		}
	}

	return 0;
}