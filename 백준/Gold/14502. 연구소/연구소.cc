#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;	// 3 ≤ N, M ≤ 8
vector<vector<int>> v;
vector<pair<int, int>> empty_walls;
vector<pair<int, int>> selected;
int max_safe = 0;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

// 3개의 벽을 세울 좌표 조합 생성 함수
void combination(int index, int count) {
    if (count == 3) {
        // 벽 3개를 세운 후 BFS 실행
        vector<vector<int>> v_copy = v;
        for (auto pa : selected)  // 벽 세우기
            v_copy[pa.first][pa.second] = 1;

        queue<pair<int, int>> q;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (v_copy[i][j] == 2) q.push({i, j});  // 바이러스 위치 저장

        // BFS로 바이러스 확산
        while (!q.empty()) {
            int cx = q.front().first;
            int cy = q.front().second;
            q.pop();

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && v_copy[nx][ny] == 0) {
                    v_copy[nx][ny] = 2;
                    q.push({nx, ny});
                }
            }
        }

        // 안전 영역 크기 계산
        int safe_area = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (v_copy[i][j] == 0) safe_area++;

        max_safe = max(max_safe, safe_area);
        return;
    }

    for (int i = index; i < empty_walls.size(); i++) {  // ✅ 인덱스 범위 수정
        selected.push_back(empty_walls[i]);
        combination(i + 1, count + 1);
        selected.pop_back();
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;
    v.assign(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++) {
            cin >> v[i][j];
            if (v[i][j] == 0) empty_walls.push_back({i, j});
        }

    combination(0, 0);
    cout << max_safe << '\n';

    return 0;
}
