#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, K;
    cin >> N >> K;

    vector<int> v(N);
    for (int i = 0; i < N; i++) cin >> v[i];

    vector<int> count(100001, 0);
    int st = 0, en = 0;
    int res = 0;

    while (en < N) {
        count[v[en]]++;  // 현재 수 증가

        // 만약 K 초과면 시작 포인터를 움직임
        while (count[v[en]] > K) {
            count[v[st]]--;
            st++;
        }

        // 현재 구간의 길이 저장
        res = max(res, en - st + 1);
        en++;
    }

    cout << res << "\n";
    return 0;
}
