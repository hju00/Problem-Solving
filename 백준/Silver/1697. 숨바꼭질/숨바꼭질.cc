#include <iostream>
#include <queue>

using namespace std;

#define MAX 200000

int N, K;
int dist[MAX + 1];  // 최소 시간 저장

void bfs() {
    queue<int> q;
    q.push(N);
    dist[N] = 0;

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        // 순간이동(2*X)
        if (cur * 2 <= MAX && dist[cur * 2] == -1) {
            dist[cur * 2] = dist[cur] + 1;
            q.push(cur * 2);
        }
        // 앞으로 한 칸(X+1)
        if (cur + 1 <= MAX && dist[cur + 1] == -1) {
            dist[cur + 1] = dist[cur] + 1;
            q.push(cur + 1);
        }
        // 뒤로 한 칸(X-1)
        if (cur - 1 >= 0 && dist[cur - 1] == -1) {
            dist[cur - 1] = dist[cur] + 1;
            q.push(cur - 1);
        }
    }
}

int main() {
    cin >> N >> K;

    fill(dist, dist + MAX + 1, -1);  
    bfs();

    cout << dist[K] << "\n"; 
    return 0;
}
