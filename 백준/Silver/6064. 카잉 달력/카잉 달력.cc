#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int T;
    cin >> T;

    for(int test_case = 1; test_case <= T; test_case++) {
        int M, N, x, y;
        cin >> M >> N >> x >> y;

        int k = x; // x에서 시작
        while (k <= M * N) { // M씩 점프하면서 y를 찾음
            if ((k - 1) % N + 1 == y) { // y가 맞으면 출력
                cout << k << '\n';
                break;
            }
            k += M; // M씩 증가
        }

        if (k > M * N) cout << "-1\n"; // 못 찾으면 -1 출력
    }

    return 0;
}