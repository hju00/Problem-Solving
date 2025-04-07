#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;	// 1 <= N <= 600, 1 <= M <= 600
vector<vector<char>> map;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void bfs(pair<int, int> s)
{
	int cnt = 0;
	queue<pair<int, int>> q;
	vector<vector<bool>> visited(N, vector<bool>(M, false));

	visited[s.first][s.second] = true;
	q.push(s);

	while(!q.empty())
	{
		int c_x = q.front().first;
		int c_y = q.front().second;
		q.pop();
		
		if(map[c_x][c_y] == 'P')	cnt++;

		for(int d = 0; d < 4; d++)	{
			int n_x = c_x + dx[d];
			int n_y = c_y + dy[d];

			if(n_x >= 0 && n_x < N && n_y >= 0 && n_y < M)	{
				if(map[n_x][n_y] != 'X' && !visited[n_x][n_y])	{
					visited[n_x][n_y] = true;
					q.push({n_x, n_y});
				}
			}
		}
	}

	if(cnt)	cout << cnt << '\n';
	else	cout << "TT\n";

}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;
	map.assign(N, vector<char>(M, ' '));

	pair<int, int> start;
	for(int i = 0; i < N; i++)
		for(int j = 0; j < M; j++) {
			cin >> map[i][j];
			if(map[i][j] == 'I')	start = {i, j};
		}
	
	bfs(start);
	
	return 0;
}