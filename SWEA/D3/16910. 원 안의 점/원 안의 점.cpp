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
		int N, cnt = 0;	// (1 <= N <= 200)
		cin >> N;

		for (int i = 0; i * i <= N * N; i++) {
			for (int j = 1; j * j <= N * N; j++) {
				if (i * i + j * j <= N * N)	cnt++;
			}
		}

		cnt = (cnt) * 4 + 1;
		cout << "#" << test_case << " " << cnt << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}