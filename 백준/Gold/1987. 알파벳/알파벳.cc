#include <iostream>
#include <vector>
using namespace std;

int R, C, max_len = 0;
vector<vector<char>> board;
bool visited[26]; // 알파벳 방문 체크
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void dfs(int x, int y, int depth) {
    max_len = max(max_len, depth);

    for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
            int alpha_idx = board[nx][ny] - 'A';
            if (!visited[alpha_idx]) {
                visited[alpha_idx] = true;
                dfs(nx, ny, depth + 1);
                visited[alpha_idx] = false; // 백트래킹
            }
        }
    }
}

int main() {
    cin >> R >> C;
    board.assign(R, vector<char>(C));

    for (int i = 0; i < R; i++)
        for (int j = 0; j < C; j++)
            cin >> board[i][j];

    visited[board[0][0] - 'A'] = true;
    dfs(0, 0, 1);

    cout << max_len << '\n';
    return 0;
}