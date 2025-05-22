#include<iostream>
#include<string>
#include<deque>
using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int n;
        string s;
        cin >> n >> s;

        deque<int> dq;
        int ans = 0;

        for(int i = 0; i < s.size(); i++)
        {   
            if(s[i] == '+')    {
                dq.push_back(s[++i] - 48);
            }
            else if(s[i] == '*')   {
                int t = dq.back() * (s[++i] - 48);
                dq.pop_back();
                dq.push_back(t);
            }
            else    {
                dq.push_back(s[i] - 48);
            }
        }

        for(int i : dq) ans += i;

        cout << "#" << test_case << " " << ans << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}