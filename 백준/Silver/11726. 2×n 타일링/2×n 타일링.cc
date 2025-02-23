#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int dp[1001];  // long long이 필요 없음 (10007보다 작기 때문)
    int n;
    cin >> n;

    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;  // 매번 10007로 나눠줌
    }

    cout << dp[n] << "\n";
    return 0;
}
