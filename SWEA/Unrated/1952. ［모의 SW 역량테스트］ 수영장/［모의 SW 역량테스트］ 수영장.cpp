#include<iostream>
#include<algorithm>
using namespace std;

int T;
int price[4];       // 1일, 1달, 3달, 1년 요금
int plan[13];       // 월별 이용 계획 (1~12월)
int dp[13];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> T;
    for (int test_case = 1; test_case <= T; test_case++) {

        for (int i = 0; i < 4; i++) cin >> price[i];
        for (int i = 1; i <= 12; i++) cin >> plan[i];

        fill(dp, dp + 13, 1e9);
        dp[0] = 0;

        for (int i = 1; i <= 12; i++) {
            // 1일권으로 i월 처리
            dp[i] = min(dp[i], dp[i - 1] + plan[i] * price[0]);

            // 1달권으로 i월 처리
            dp[i] = min(dp[i], dp[i - 1] + price[1]);

            // 3달권으로 처리 (i >= 3)
            if (i >= 3)
                dp[i] = min(dp[i], dp[i - 3] + price[2]);
            else
                dp[i] = min(dp[i], price[2]); // i < 3일 때 처음부터 3달권 쓸 수 있음
        }

        // 마지막에 1년권과 비교
        int answer = min(dp[12], price[3]);

        cout << "#" << test_case << " " << answer << "\n";
    }
    return 0;
}
