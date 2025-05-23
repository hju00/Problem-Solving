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
		int N;	//  (2 ≤ N ≤ 30)
		cin >> N;

		int dist = 0, speed = 0;

		while (N--)
		{
			int command;
			cin >> command;

			switch (command)
			{
			case 0:
				dist += speed;
				break;
			case 1:
				int boost;
				cin >> boost;
				speed += boost;
				dist += speed;
				break;
			case 2:
				int brake;
				cin >> brake;
				speed -= brake;
				speed = (speed <= 0) ? 0 : speed;
				dist += speed;
				break;
			}
		}

		cout << "#" << test_case << " " << dist << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}