#include<iostream>
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
        int tc;
        cin >> tc;

        deque<int> dq;

        for(int i = 0; i < 8; i++)  {
            int temp;
            cin >> temp;
            dq.push_back(temp);
        }
        
        bool check = true;
        while(check)
        {
            for(int i = 1; i <= 5; i++) {
                int nx = dq.front() - i;
                if(nx <= 0) {
                    dq.push_back(0);
                    check = false;
                }
                else    dq.push_back(nx);

                dq.pop_front();
                if(!check)  break;
            }
        }

        cout << "#" << tc << " ";
        for(int d : dq) cout << d << " ";
        cout << "\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}