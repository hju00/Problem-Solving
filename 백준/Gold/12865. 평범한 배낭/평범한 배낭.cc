#include <iostream>
#include <vector>
#include <cstring> // memset 사용
using namespace std;

int N, K;
vector<pair<int, int>> v(100);
int dp[101][100001];

int dfs(int idx, int kg) {
    if (idx == N) return 0; // 모든 물건을 확인했을 때 종료

    // 이미 계산한 값이 있다면 반환
    if (dp[idx][kg] != -1) return dp[idx][kg];

    int without = dfs(idx + 1, kg); // 물건을 선택하지 않는 경우
    int with = 0;

    if (kg + v[idx].first <= K) // 용량을 초과하지 않을 경우만 선택
        with = dfs(idx + 1, kg + v[idx].first) + v[idx].second;

    return dp[idx][kg] = max(without, with);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> K;

    for (int i = 0; i < N; i++)
        cin >> v[i].first >> v[i].second;

    memset(dp, -1, sizeof(dp)); // dp 배열을 -1로 초기화

    cout << dfs(0, 0) << endl;

    return 0;
}
