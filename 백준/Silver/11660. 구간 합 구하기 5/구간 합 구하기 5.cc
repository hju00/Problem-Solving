#include <iostream>
#include <vector>
using namespace std;

int N, M;	// 1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000
vector<vector<int>> v;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int temp;
	cin >> N >> M;
	v.assign(N + 1, vector<int>(N + 1, 0));

	for(int i = 1; i <= N; i++)
		for(int j = 1; j <= N; j++)	{
			cin >> temp;
			v[i][j] = v[i][j - 1] + temp;
		}

	while(M--)
	{
		int x1, y1, x2, y2;
		int ans = 0;
		cin >> x1 >> y1 >> x2 >> y2;

		for(int i = x1; i <= x2; i++)	{
			ans += v[i][y2];
			ans -= v[i][y1 - 1];
		}

		cout << ans << '\n';		
	}

	return 0;
}