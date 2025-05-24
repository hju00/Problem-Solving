#include<iostream>
#include<vector>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		int N, K, ans = 0;	//  (5 ≤ N ≤ 15), (2 ≤ K ≤ N)
		cin >> N >> K;
		vector<vector<int>> v(N, vector<int>(N, 0));

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				cin >> v[i][j];

		// 가로 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - K; j++)
			{
				bool ch = true;
				for (int k = 0; k < K; k++)
					if (v[i][j + k] != 1) ch = false;

				if ((j + K < N && v[i][j + K] == 1) || (j - 1 >= 0 && v[i][j - 1] == 1)) ch = false;
				if (ch) ans++;
			}
		}

		// 세로 확인
		for (int j = 0; j < N; j++) {
			for (int i = 0; i <= N - K; i++)
			{
				bool ch = true;
				for (int k = 0; k < K; k++)
					if (v[i + k][j] != 1) ch = false;

				if ((i + K < N && v[i + K][j] == 1) || (i - 1 >= 0 && v[i - 1][j] == 1)) ch = false;
				if (ch) ans++;
			}
		}

		cout << "#" << test_case << " " << ans << "\n";
	}
	return 0;
}
