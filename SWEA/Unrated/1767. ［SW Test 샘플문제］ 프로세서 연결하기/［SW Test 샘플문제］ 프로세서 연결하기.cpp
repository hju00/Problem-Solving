#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<vector<int>> board;
vector<pair<int, int>> cores;
int max_connected, min_wire_length;

// 방향 (상, 하, 좌, 우)
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

// 전선을 깔 수 있는지 체크하는 함수
bool can_place_wire(int x, int y, int dir) {
    int nx = x + dx[dir];
    int ny = y + dy[dir];

    while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
        if (board[nx][ny] != 0) return false;  // 다른 프로세서나 전선이 있으면 불가능
        nx += dx[dir];
        ny += dy[dir];
    }
    return true;
}

// 전선 깔기 (value = 2), 전선 지우기 (value = 0)
int place_wire(int x, int y, int dir, int value) {
    int nx = x + dx[dir];
    int ny = y + dy[dir];
    int length = 0;

    while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
        board[nx][ny] = value;
        nx += dx[dir];
        ny += dy[dir];
        length++;
    }
    return length;
}

// DFS 백트래킹
void dfs(int index, int connected, int wire_length) {
    // 모든 코어를 다 확인했을 때 최적의 값을 갱신
    if (index == cores.size()) {
        if (connected > max_connected) {
            max_connected = connected;
            min_wire_length = wire_length;
        } else if (connected == max_connected) {
            min_wire_length = min(min_wire_length, wire_length);
        }
        return;
    }

    int x = cores[index].first;
    int y = cores[index].second;

    // 네 방향으로 전선 연결 시도
    bool placed = false;
    for (int d = 0; d < 4; d++) {
        if (can_place_wire(x, y, d)) {
            placed = true;
            int length = place_wire(x, y, d, 2);  // 전선 깔기
            dfs(index + 1, connected + 1, wire_length + length);
            place_wire(x, y, d, 0);  // 전선 제거 (백트래킹)
        }
    }

    // 전선을 깔 수 없는 경우, 그냥 다음 코어로 진행
    dfs(index + 1, connected, wire_length);
}

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;
        board = vector<vector<int>>(N, vector<int>(N));
        cores.clear();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> board[i][j];
                if (board[i][j] == 1) {
                    if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;  // 가장자리 코어는 이미 연결됨
                    cores.push_back({i, j});
                }
            }
        }

        max_connected = 0;
        min_wire_length = 1e9;
        dfs(0, 0, 0);

        cout << "#" << test_case << " " << min_wire_length << endl;
    }

    return 0;
}
