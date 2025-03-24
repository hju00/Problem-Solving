#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
vector<int> adj[32001];  // 학생 관계를 저장하는 그래프
int indegree[32001];     // 진입 차수 배열

void topology_sort() {
    queue<int> q;
    
    // 1. 진입 차수가 0인 노드를 큐에 삽입
    for (int i = 1; i <= N; i++) {
        if (indegree[i] == 0) q.push(i);
    }

    // 2. 위상 정렬 수행
    while (!q.empty()) {
        int cur = q.front();
        q.pop();
        cout << cur << " ";

        // 3. 현재 노드와 연결된 간선 제거
        for (int next : adj[cur]) {
            indegree[next]--;  // 연결된 노드의 진입 차수 감소
            if (indegree[next] == 0) q.push(next);  // 새롭게 진입 차수가 0이 된 노드를 큐에 추가
        }
    }
    cout << '\n';
}

int main() {
    cin >> N >> M;  // 학생 수(N), 비교 횟수(M)

    for (int i = 0; i < M; i++) {
        int A, B;
        cin >> A >> B;
        adj[A].push_back(B);  // A → B 방향 간선 추가
        indegree[B]++;        // B의 진입 차수 증가
    }
    
    topology_sort();  // 위상 정렬 실행
    return 0;
}