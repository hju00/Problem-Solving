#include<iostream>
#include<string>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int tc, ans = 0;
        string target, s;
        cin >> tc >> target >> s;

        for(int i = 0; i < s.size() - target.size() + 1; i++)   {
            if(!target.compare(s.substr(i, target.size())))  ans++;
        }
        cout << "#" << tc << " " << ans << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}