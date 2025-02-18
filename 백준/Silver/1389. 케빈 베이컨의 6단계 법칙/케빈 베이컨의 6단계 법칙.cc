#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9; // 무한대 값 설정

int main() {
    int N, M;
    cin >> N >> M;

    vector<vector<int>> dist(N + 1, vector<int>(N + 1, INF));

    // 자기 자신으로 가는 거리는 0
    for (int i = 1; i <= N; i++) dist[i][i] = 0;

    // 친구 관계 입력
    for (int i = 0; i < M; i++) {
        int A, B;
        cin >> A >> B;
        dist[A][B] = 1;
        dist[B][A] = 1;
    }

    // 플로이드-워셜 알고리즘 실행
    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][k] == INF || dist[k][j] == INF) continue; // 오버플로우 방지
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }

    // 최소 케빈 베이컨 수를 가진 사람 찾기
    int minSum = INF, answer = -1;
    for (int i = 1; i <= N; i++) {
        int sum = 0;
        for (int j = 1; j <= N; j++) {
            sum += dist[i][j];
        }
        if (sum < minSum) {
            minSum = sum;
            answer = i;
        }
    }

    cout << answer << "\n";
    return 0;
}
