#include<iostream>
#include<string>
using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N;  // 10 ≤ N ≤ 100
        string s;

        cin >> N >> s;

        for(int i = 0; i < s.length() - 1; i++)
        {
            if(s[i] == s[i + 1])    {
                s.erase(i, 2); 
                i = -1;     // 다시 처음으로 돌아가기 위함
            }
        }

        cout << "#" << test_case << " " << s << "\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}