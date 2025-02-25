#include <iostream>
#include <vector>
#include <queue>
#define INF 1e9 // 무한대를 의미하는 값 (1,000,000,000)
using namespace std;

int V, E, K;
vector<vector<pair<int, int>>> graph; // 인접 리스트 (각 정점에서 갈 수 있는 정점과 가중치 저장)
vector<int> dist; // 최단 거리 배열

void dijkstra(int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    pq.push({0, start}); // 시작 정점을 큐에 삽입 (거리, 정점)
    dist[start] = 0;

    while (!pq.empty()) { // 큐가 빌 때까지 반복
        int cur_dist = pq.top().first; // 현재 노드까지의 최단 거리
        int cur = pq.top().second; // 현재 노드
        pq.pop();

        if (cur_dist > dist[cur]) continue; // 이미 처리된 노드면 무시

        for (auto next : graph[cur]) { // 현재 노드와 연결된 모든 노드 탐색
            int next_node = next.first;
            int weight = next.second;

            if (dist[next_node] > dist[cur] + weight) { // 더 짧은 경로 발견
                dist[next_node] = dist[cur] + weight; // 최단 거리 갱신
                pq.push({dist[next_node], next_node}); // 우선순위 큐에 삽입
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> V >> E >> K;
    graph.assign(V + 1, vector<pair<int, int>>()); // 그래프 초기화
    dist.assign(V + 1, INF); // 최단 거리 배열 초기화 (INF)

    int u, v, w;
    for (int i = 0; i < E; i++) { // 간선 입력
        cin >> u >> v >> w;
        graph[u].push_back({v, w}); // 방향 그래프이므로 한쪽만 저장
    }

    dijkstra(K); // 다익스트라 알고리즘 실행

    for (int i = 1; i <= V; i++) { // 결과 출력
        if (dist[i] == INF) cout << "INF\n"; // 도달할 수 없는 경우
        else cout << dist[i] << "\n"; // 최단 거리 출력
    }

    return 0;
}