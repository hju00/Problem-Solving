#include<iostream>
#include<string>
#include<deque>
using namespace std;

char open[4] = {'(', '[', '{', '<'};
char close[4] = {')', ']', '}', '>'};

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int len;
        string s;
        cin >> len >> s;

        deque<char> dq;
        bool is_valid = true;

        for(char c : s)
        {

            for(int i = 0; i < 4; i++)  {

                if(c == open[i])    {
                    dq.push_back(c);
                    break;
                }
                else if(c == close[i])  {
                    if(dq.back() == open[i])    {
                        dq.pop_back();
                        break;
                    }
                    else    {
                        is_valid = false;
                        break;
                    }
                }
            }

            if(is_valid)    continue;
            else    break;
        }

        if(is_valid)   cout << "#" << test_case << " 1\n";
        else    cout << "#" << test_case << " 0\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}