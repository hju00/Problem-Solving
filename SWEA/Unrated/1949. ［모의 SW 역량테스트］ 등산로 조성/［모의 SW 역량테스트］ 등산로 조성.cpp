#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N, K, max_len;
int board[8][8];
bool visited[8][8];
int dy[4] = {0, 0, 1, -1};
int dx[4] = {1, -1, 0, 0};

void dfs(int y, int x, int length, bool cutUsed)
{
    max_len = max(max_len, length);

    for(int d = 0; d < 4; d++) {
        int ny = y + dy[d];
        int nx = x + dx[d];

        if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;

        if(board[ny][nx] < board[y][x]) {
            visited[ny][nx] = true;
            dfs(ny, nx, length + 1, cutUsed);
            visited[ny][nx] = false;
        }
        else if(!cutUsed && board[ny][nx] - K < board[y][x]) {
            int original = board[ny][nx];
            board[ny][nx] = board[y][x] - 1; // 최소 1 낮게 깎기
            visited[ny][nx] = true;
            dfs(ny, nx, length + 1, true);
            visited[ny][nx] = false;
            board[ny][nx] = original; // 원상복구
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int T; cin >> T;

    for(int tc = 1; tc <= T; ++tc) {
        cin >> N >> K;
        int highest = 0;
        max_len = 0;

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                cin >> board[i][j];
                highest = max(highest, board[i][j]);
                visited[i][j] = false;
            }

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(board[i][j] == highest) {
                    visited[i][j] = true;
                    dfs(i, j, 1, false); // 시작점, 길이 1, 아직 안 깎음
                    visited[i][j] = false;
                }

        cout << "#" << tc << " " << max_len << "\n";
    }
}