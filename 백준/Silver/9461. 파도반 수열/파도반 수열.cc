#include <iostream>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, T;	// 1 ≤ N ≤ 100
	cin >> T;

	long p[101];
	p[1] = 1;
	p[2] = 1;
	p[3] = 1;
	p[4] = 2;
	p[5] = 2;
	
	for(int i = 6; i <= 100; i++)
		p[i] = p[i - 1] + p[i - 5];
	
	while(T--)
	{
		cin >> N;
		cout << p[N] << '\n';
	}

	return 0;
}