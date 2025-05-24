#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int test_case;
	int T;

	cin >> T;

	for (test_case = 1; test_case <= T; ++test_case)
	{
		int N;	// (1 ≤ N ≤ 10)
		int tri[11][11];
		cin >> N;

		tri[1][1] = 1;

		for (int i = 2; i <= N; i++)
		{
			for (int j = 1; j <= i; j++)
			{
				tri[i][j] = tri[i - 1][j - 1] + tri[i - 1][j];
			}
		}

		cout << "#" << test_case << "\n";
		for (int i = 1; i <= N; i++)
		{
			for (int j = 1; j <= i; j++)
			{
				cout << tri[i][j] << " ";
			}
			cout << "\n";
		}
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}