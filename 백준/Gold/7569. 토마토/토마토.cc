#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
using namespace std;
 
int box[101][101][101];
queue<pair<int, pair<int, int>>> q;
int dx[6] = { 1, 0, -1, 0, 0, 0 };
int dy[6] = { 0, 1, 0, -1, 0, 0 };
int dz[6] = { 0, 0 , 0, 0, 1, -1 };

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int N, M, H;
	cin >> M >> N >> H;

	for (int i = 0; i < H; i++)
		for (int j = 0; j < N; j++)
			for (int k = 0; k < M; k++) {
				cin >> box[i][j][k];
				if (box[i][j][k] == 1)
					q.push({i, {j, k}});
			}
 
	while (!q.empty()) {
		auto qfront = q.front();
		q.pop();

		int curz = qfront.first;
		int cury = qfront.second.first;
		int curx = qfront.second.second;
		int order = box[curz][cury][curx];

		for (int dir = 0; dir < 6; dir++) {
			int nz = curz + dz[dir];
			int ny = cury + dy[dir];
			int nx = curx + dx[dir];

			if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (!box[nz][ny][nx]) {
					q.push(make_pair(nz, make_pair(ny, nx)));
					box[nz][ny][nx] = order + 1;
				}
			}
		}
	}
	int max_v = 0;
	for (int i = 0; i < H; i++)
		for (int j = 0; j < N; j++)
			for (int k = 0; k < M; k++) {
				if (box[i][j][k]) {
					max_v = max(max_v, box[i][j][k]);
				}
				else {
					cout << "-1\n";
					return 0;
				}
			}
			
	cout << max_v - 1 << '\n';
	return 0;
}
