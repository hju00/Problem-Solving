#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        //  M초의 시간 동안 K개의 붕어빵
        int N, M, K;    // (1 ≤ N, M, K ≤ 100)
        cin >> N >> M >> K;

        vector<int> sec(11112, 0);
        while(N--)  {
            int t;
            cin >> t;
            sec[t]++;
        }

        int cnt = 0;
        bool possible = true;

        for(int i = 0; i <= 11111; i++)  {
            if(i > 0 && i % M == 0)  cnt += K;
            cnt -= sec[i];
            if(cnt < 0) {
                possible = false;
                break;
            }
        }

        if(possible)    cout << "#" << test_case << " Possible\n";
        else            cout << "#" << test_case << " Impossible\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}