#include <iostream>
#include <queue>
using namespace std;

int N; // (3 ≤ N ≤ 16)
int home[17][17];	
int dp[17][17][3];	// [0]→, [1]↓, [2]↘
int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for(int i = 1; i <= N; i++)	{
		for(int j = 1; j <= N; j++)	{
			cin >> home[i][j];
		}
	}

	dp[1][2][0] = 1;

	for(int y = 1; y <= N; y++)	{
		for(int x = 3; x <= N; x++)	{
			if(home[y][x] == 1)	continue;

			//	가로
			dp[y][x][0] = dp[y][x - 1][0] + dp[y][x - 1][2];

			//	세로
			dp[y][x][1] = dp[y - 1][x][1] + dp[y - 1][x][2];

			//	대각선
			if(home[y - 1][x] != 1 && home[y][x - 1] != 1)
				dp[y][x][2] = dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
		}
	}

	cout << dp[N][N][0] + dp[N][N][1] + dp[N][N][2] << '\n';

	return 0;
}