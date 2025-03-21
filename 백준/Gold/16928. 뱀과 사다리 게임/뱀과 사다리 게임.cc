#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> road;

void bfs() {	
	// {현재 위치한 칸의 번호, 주사위를 굴린 횟수}
	queue<pair<int, int>> q;
	vector<bool> visited(101, false);
	
	q.push({1, 0});
	visited[1] = true;

	while (!q.empty()) {
		int cur = q.front().first;
		int dice = q.front().second;
		q.pop();

		if (cur == 100) {
			cout << dice << '\n';
			return;
		}

		for (int d = 1; d <= 6; d++) {
			int next = cur + d;
			if (next > 100 || visited[next]) continue;

			// 사다리나 뱀이 있는 경우
			if (road[next] != 0) 
				next = road[next];

			// 최종 목적지 next 방문 처리
			if (!visited[next]) {
				visited[next] = true;
				q.push({next, dice + 1});
			}
		}
	}
}

int main() {
	int N, M, a, b;
	cin >> N >> M;

	road.assign(101, 0);

	for (int i = 0; i < N + M; i++) {
		cin >> a >> b;
		road[a] = b;
	}

	bfs();
	
	return 0;
}