#include<iostream>
#include<string>

using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        string tar;
        cin >> tar;

        int change_cnt = 0;
        bool exist_one = false;

        for(int i = 0; i < tar.size(); i++)
        {
            if(tar[i] == '0') {
                if(exist_one)   {
                    change_cnt++;
                    exist_one = false;
                }
                else    continue;
            }
            else if(tar[i] == '1')  {
                if(exist_one)   continue;
                else    {
                    change_cnt++;
                    exist_one = true;
                }
            }
        }

        cout << "#" << test_case << " " << change_cnt << "\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}