#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int tc, N, M;
        cin >> tc >> N >> M;

        long long ans = 1;

        while(M--)  ans *= N;

        cout << "#" << tc << " " << ans << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}