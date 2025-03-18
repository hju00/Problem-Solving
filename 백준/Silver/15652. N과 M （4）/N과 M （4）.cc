#include <iostream>
using namespace std;

int N, M;
int arr[8];

void backtrack(int depth, int start) {
    if (depth == M) {
        for (int i = 0; i < M; i++)
            cout << arr[i] << " ";
        cout << '\n';
        return;
    }

    for (int i = start; i <= N; i++) { // 중복 가능하므로 start부터 탐색
        arr[depth] = i;
        backtrack(depth + 1, i);
    }
}

int main() {
    cin >> N >> M;
    backtrack(0, 1);
    return 0;
}
