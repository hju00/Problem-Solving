#include <iostream>
#include <vector>

using namespace std;

int board[19][19];
int dx[4] = {0, 1, 1, -1}; // →, ↘, ↓, ↗ 방향
int dy[4] = {1, 1, 0, 1};

// 배열 범위를 벗어나지 않는지 체크하는 함수
bool is_valid(int x, int y) {
    return x >= 0 && x < 19 && y >= 0 && y < 19;
}

// 오목을 찾는 함수
bool check_win(int x, int y, int color, int &res_x, int &res_y) {
    for (int d = 0; d < 4; d++) { 
        int count = 1;
        int px = x - dx[d], py = y - dy[d];

        // 현재 방향의 반대편에 같은 색 돌이 있으면 무효 (가장 왼쪽 or 위쪽 돌 찾기)
        if (is_valid(px, py) && board[px][py] == color)
            continue;

        // 5개의 돌이 연속되는지 확인
        for (int i = 1; i < 5; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            if (!is_valid(nx, ny) || board[nx][ny] != color)	break;
            count++;
        }

        // 6목인지 체크
        int nx = x + dx[d] * 5, ny = y + dy[d] * 5;
        if (count == 5 && (!is_valid(nx, ny) || board[nx][ny] != color)) {
            res_x = x + 1; // 1-based index
            res_y = y + 1;
            return true;
        }
    }
    return false;
}

int main() {
    for (int i = 0; i < 19; i++)
        for (int j = 0; j < 19; j++)
            scanf("%d", &board[i][j]);

    for (int i = 0; i < 19; i++) {
        for (int j = 0; j < 19; j++) {
            if (board[i][j] != 0) {
                int res_x, res_y;
                if (check_win(i, j, board[i][j], res_x, res_y)) {
                    cout << board[i][j] << "\n" << res_x << " " << res_y << "\n";
                    return 0;
                }
            }
        }
    }
    cout << "0\n"; // 승자가 없는 경우
    return 0;
}
