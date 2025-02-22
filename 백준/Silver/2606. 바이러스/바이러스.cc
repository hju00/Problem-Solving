#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, T;
vector<vector<int>> computers;

int bfs(int start) 
{
    vector<bool> c(N + 1, false);  // 방문 여부
    queue<int> q;
    int cnt = 0;

    q.push(start);
    c[start] = true;

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int i = 1; i <= N; i++) {
            if (computers[cur][i] == 1 && !c[i]) {  // 연결되어 있고 방문 안 했으면
                q.push(i);
                c[i] = true;
                cnt++;
            }
        }
    }

    return cnt;
}

int main() {

    cin >> N >> T;
    computers.assign(N + 1, vector<int>(N + 1, 0));

    int a, b;
    for (int i = 0; i < T; i++) {
        cin >> a >> b;
        computers[a][b] = 1;
        computers[b][a] = 1;
    }

    cout << bfs(1) << "\n";  // 1번 컴퓨터에서 시작
    return 0;
}
