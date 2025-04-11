#include <iostream>
#include <vector>
using namespace std;

int N;	// 2 ≤ N ≤ 100,000
vector<int> v[100001];
vector<int> ans(100001);

void dfs(int current, int parent)
{
	for(int next : v[current])	{
		if(next != parent)	{
			ans[next] = current;
			dfs(next, current);
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> N;

	for(int i = 1; i < N; i++) {
		int a, b;
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}

	dfs(1, 0);

	for(int i = 2; i <= N; i++)
		cout << ans[i] << '\n';

	return 0;
}