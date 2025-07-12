#include <iostream>
#include <vector>

using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, M, K;    // (1 ≤ N, M ≤ 15)
	cin >> N >> M >> K;

	vector<vector<int>> v(N + 1, vector<int>(M + 1, 0));
	for (int i = 1; i <= N; i++)
		v[i][1] = 1;
	for (int j = 1; j <= M; j++)
		v[1][j] = 1;

	if (K == 0) {
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= M; j++) {
				v[i][j] = v[i - 1][j] + v[i][j - 1];
			}
		}
	}
	else {
		// K번째 칸의 좌표
		int kr = (K - 1) / M + 1;
		int kc = (K - 1) % M + 1;

		// (1,1) -> (kr,kc)까지 DP 계산
		for (int i = 2; i <= kr; i++) {
			for (int j = 2; j <= kc; j++) {
				v[i][j] = v[i - 1][j] + v[i][j - 1];
			}
		}

		// 이후 경로 초기화: (kr,kc) 기준으로 다시 시작
		for (int i = kr + 1; i <= N; i++) {
			v[i][kc] = v[i - 1][kc];
		}
		for (int j = kc + 1; j <= M; j++) {
			v[kr][j] = v[kr][j - 1];
		}

		for (int i = kr + 1; i <= N; i++) {
			for (int j = kc + 1; j <= M; j++) {
				v[i][j] = v[i - 1][j] + v[i][j - 1];
			}
		}
	}

	cout << v[N][M] << "\n";
	return 0;
}
