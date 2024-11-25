#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
    string A, B;
    cin >> A >> B;

    int m = A.size(), n = B.size();

    // DP 테이블 초기화
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

    // DP 점화식 적용
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (A[i - 1] == B[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1; // 문자가 같을 경우
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]); // 문자가 다를 경우
            }
        }
    }

    // 최종 결과 출력
    cout << dp[m][n] << endl;

    return 0;
}
