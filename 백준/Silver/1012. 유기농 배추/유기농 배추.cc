#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int T, M, N, K;
vector<vector<int>> field;
vector<vector<bool>> visited;


int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};


void bfs(int x, int y) {
    queue<pair<int, int>> q;
    q.push({x, y});
    visited[y][x] = true;

    while (!q.empty())
	{
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (field[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.push({nx, ny});
                }
            }
        }
    }
}

int main() {
    cin >> T;

    while (T--) {
        cin >> M >> N >> K;

        field.assign(N, vector<int>(M, 0));
        visited.assign(N, vector<bool>(M, false));

        for (int i = 0; i < K; i++) {
            int x, y;
            cin >> x >> y;
            field[y][x] = 1;  // (y, x) 좌표에 배추 존재
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (field[i][j] == 1 && !visited[i][j]) {
                    bfs(j, i);
                    count++;
                }
            }
        }

        cout << count << "\n";
    }

    return 0;
}
