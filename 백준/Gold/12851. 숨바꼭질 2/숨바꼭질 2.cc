#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX = 100001;

int main() {
    int N, K;
    cin >> N >> K;

    vector<int> time(MAX, -1); // 각 위치까지의 최소 시간
    vector<int> count(MAX, 0); // 각 위치까지의 최소 시간으로 도달하는 방법의 수

    queue<int> q;
    q.push(N);
    time[N] = 0;
    count[N] = 1;

    while (!q.empty()) {
        int current = q.front();
        q.pop();

        for (int next : {current - 1, current + 1, current * 2}) {
            if (next < 0 || next >= MAX) continue;

            if (time[next] == -1) {
                // 아직 방문하지 않은 위치
                time[next] = time[current] + 1;
                count[next] = count[current];
                q.push(next);
            } else if (time[next] == time[current] + 1) {
                // 이미 방문한 위치지만, 같은 최소 시간으로 도달하는 경우
                count[next] += count[current];
            }
        }
    }

    cout << time[K] << '\n' << count[K] << '\n';

    return 0;
}