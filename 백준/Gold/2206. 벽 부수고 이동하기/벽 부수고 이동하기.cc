#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;
vector<vector<char>> map;
int visited[1001][1001][2];  // visited[x][y][0 or 1]: 벽 안 부쉈을 때 / 부쉈을 때

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

struct State {
	int x, y, broken;
};

int bfs() {
	queue<State> q;
	q.push({1, 1, 0});
	visited[1][1][0] = 1;

	while (!q.empty()) {
		State cur = q.front();
		q.pop();

		if (cur.x == N && cur.y == M) return visited[cur.x][cur.y][cur.broken];

		for (int d = 0; d < 4; d++) {
			int nx = cur.x + dx[d];
			int ny = cur.y + dy[d];

			if (nx < 1 || ny < 1 || nx > N || ny > M) continue;

			// 빈 칸이면 그대로 이동
			if (map[nx][ny] == '0' && visited[nx][ny][cur.broken] == 0) {
				visited[nx][ny][cur.broken] = visited[cur.x][cur.y][cur.broken] + 1;
				q.push({nx, ny, cur.broken});
			}

			// 벽인데 아직 안 부쉈다면 부수고 이동
			if (map[nx][ny] == '1' && cur.broken == 0 && visited[nx][ny][1] == 0) {
				visited[nx][ny][1] = visited[cur.x][cur.y][cur.broken] + 1;
				q.push({nx, ny, 1});
			}
		}
	}

	return -1; // 도달 못함
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;
	map.assign(N + 1, vector<char>(M + 1));

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			cin >> map[i][j];

	cout << bfs() << '\n';
	return 0;
}