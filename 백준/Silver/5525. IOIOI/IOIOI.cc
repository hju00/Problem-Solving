#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M, ans = 0;
    cin >> N >> M;
    
    string S;
    cin >> S;

    int cnt = 0;  // 연속된 "IOI" 개수
    for (int i = 1; i < M - 1; i++) {
        if (S[i - 1] == 'I' && S[i] == 'O' && S[i + 1] == 'I') {
            cnt++;
            if (cnt >= N) ans++;  // "IOI"가 N번 연속되면 P_N 성립
            i++;  // "O" 다음 "I"에서 다시 체크
        } else {
            cnt = 0;  // 끊기면 초기화
        }
    }

    cout << ans << '\n';
    return 0;
}