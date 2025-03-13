#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N;
vector<vector<char>> map;
vector<vector<bool>> check;
vector<int> house_cnt;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void bfs(int x, int y) {
    queue<pair<int, int>> q;
    q.push({x, y});
    check[x][y] = true;
    int answer = 0;

    while (!q.empty()) {
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();
        answer++;

        for (int d = 0; d < 4; d++) {
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] == '1' && !check[nx][ny]) {
                    check[nx][ny] = true;
                    q.push({nx, ny});
                }
            }
        }
    }

    house_cnt.push_back(answer);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N;
    map.assign(N, vector<char>(N, '0'));
    check.assign(N, vector<bool>(N, false));

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> map[i][j];

    int house_group = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] == '1' && !check[i][j]) {
                bfs(i, j);
                house_group++;
            }
        }
    }

    sort(house_cnt.begin(), house_cnt.end());
    cout << house_group << '\n';
    for (auto i : house_cnt)
        cout << i << '\n';

    return 0;
}
