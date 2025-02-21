#include <iostream>

using namespace std;

int N;
int paper[128][128];
int white = 0, blue = 0;

void fun(int x, int y, int n) {
    int first_color = paper[x][y];
    bool is_same = true;

    // 현재 색종이가 한 가지 색인지 확인
    for (int i = x; i < x + n; i++) {
        for (int j = y; j < y + n; j++) {
            if (paper[i][j] != first_color) {
                is_same = false;
                break;
            }
        }
        if (!is_same) break;
    }

    // 한 가지 색이면 개수 증가 후 종료
    if (is_same) {
        if (first_color == 0) white++;
        else blue++;
        return;
    }

    // 4등분하여 재귀 호출
    int half = n / 2;
    fun(x, y, half);           // 왼쪽 위
    fun(x, y + half, half);    // 오른쪽 위
    fun(x + half, y, half);    // 왼쪽 아래
    fun(x + half, y + half, half);  // 오른쪽 아래
}

int main() {
    cin >> N;

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> paper[i][j];

    fun(0, 0, N);

    cout << white << "\n" << blue << "\n";
    return 0;
}
