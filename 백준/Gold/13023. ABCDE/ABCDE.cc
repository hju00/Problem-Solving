#include<iostream>
#include<vector>

#define endl "\n"
using namespace std;

vector<int> people[2001];
bool visit[2001];
bool answer = false;

void DFS(int length , int cur)
{
	if (length == 4)
	{
		answer = true;
		return;
	}
	for (auto i : people[cur])
	{
		if (!visit[i])
		{
			visit[i] = true;
			DFS(length + 1, i);
			visit[i] = false;
		}
	}
}

void Answer()
{
	int N, M;
	cin >> N >> M;
	int a, b;
	for (int i = 0; i < M; i++)
	{
		cin >> a >> b;
		people[a].push_back(b);
		people[b].push_back(a);
	}
	for (int i = 0; i < N; i++)
	{
		visit[i] = true;
		DFS(0, i);
		visit[i] = false;
		if (answer) break;
	}
	cout << answer;
}

int main()
{
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	Answer();
	return 0;
}