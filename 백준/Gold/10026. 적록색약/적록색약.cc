#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N;	// (1 ≤ N ≤ 100)
vector<vector<char>> paint;
vector<vector<bool>> visited;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void bfs1(int x, int y)
{
	char c = paint[x][y];
	queue<pair<int, int>> q;
	visited[x][y] = true;
	q.push({x, y});

	while(!q.empty())
	{
		int cx = q.front().first;
		int cy = q.front().second;
		q.pop();

		for(int d = 0; d < 4; d++){
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			if(nx >= 0 && nx < N && ny >= 0 && ny < N)
				if(paint[nx][ny] == c && !visited[nx][ny]){
					visited[nx][ny] = true;
					q.push({nx, ny});
				}

		}
	}

}

void bfs2(int x, int y)
{
	char c = paint[x][y];
	queue<pair<int, int>> q;
	visited[x][y] = true;
	q.push({x, y});

	while(!q.empty())
	{
		int cx = q.front().first;
		int cy = q.front().second;
		q.pop();

		for(int d = 0; d < 4; d++){
			int nx = cx + dx[d];
			int ny = cy + dy[d];

			if(nx >= 0 && nx < N && ny >= 0 && ny < N)
				if(c == 'B'){
					if(paint[nx][ny] == c && !visited[nx][ny]){
						visited[nx][ny] = true;
						q.push({nx, ny});
					}
				}
				else{
					if((paint[nx][ny] == 'R' || paint[nx][ny] == 'G') && !visited[nx][ny]){
						visited[nx][ny] = true;
						q.push({nx, ny});
					}
				}
		}
	}
}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;
	paint.assign(N, vector<char>(N, ' '));
	visited.assign(N, vector<bool>(N, false));

	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			cin >> paint[i][j];
	
	int non_color_blindness = 0;
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			if(!visited[i][j]){
				bfs1(i, j);
				non_color_blindness++;
			}

	visited.assign(N, vector<bool>(N, false));
	int color_blindness = 0;
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			if(!visited[i][j]){
				bfs2(i, j);
				color_blindness++;
			}

	cout << non_color_blindness << ' ' << color_blindness;
	return 0;
}