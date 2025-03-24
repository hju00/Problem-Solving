#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

const int MAX_N = 1000; // 최대 건물 수

vector<int> adj[MAX_N + 1]; // 건물 간의 관계
int indegree[MAX_N + 1]; // 진입 차수
int construct_time[MAX_N + 1]; // 각 건물의 건설 시간
int dp[MAX_N + 1]; // dp[i]: i번째 건물을 짓기까지 걸리는 최소 시간

int topology_sort(int n, int w) {
    queue<int> q;

    // 초기 세팅: 진입 차수가 0인 건물들을 큐에 삽입
    for (int i = 1; i <= n; i++) {
        dp[i] = construct_time[i]; // 기본 건설 시간 설정
        if (indegree[i] == 0) {
            q.push(i);
        }
    }

    // 위상 정렬 수행
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int next : adj[cur]) {
            indegree[next]--;

            // 최대 누적 시간 갱신 (이전 건물까지의 시간 + 현재 건물의 건설 시간)
            dp[next] = max(dp[next], dp[cur] + construct_time[next]);

            if (indegree[next] == 0) {
                q.push(next);
            }
        }
    }

    return dp[w]; // 목표 건물 W까지의 최종 공사 시간 반환
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int T;
    cin >> T;

    while (T--) {
        int N, K, W;
        cin >> N >> K;

        // 배열 초기화
        for (int i = 1; i <= N; i++) {
            adj[i].clear();
            indegree[i] = 0;
        }

        for (int i = 1; i <= N; i++) 
            cin >> construct_time[i];

        for (int i = 0; i < K; i++) {
            int a, b;
            cin >> a >> b;
            adj[a].push_back(b);
            indegree[b]++;
        }

        cin >> W;

        cout << topology_sort(N, W) << '\n';
    }

    return 0;
}