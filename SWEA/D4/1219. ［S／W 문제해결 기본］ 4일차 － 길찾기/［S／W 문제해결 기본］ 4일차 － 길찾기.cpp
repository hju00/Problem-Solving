#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int tc, N;
        vector<int> v[100];
        queue<int> q;
        bool can = false;

        cin >> tc >> N;

        while(N--)  {
            int a, b;
            cin >> a >> b;
            v[a].push_back(b);
        }

        for(int i : v[0])   q.push(i);

        while(!q.empty())
        {
            int s = q.front();  q.pop();

            if(s == 99) can = true;

            for(int i : v[s])   q.push(i);
        }

        if(can) cout << "#" << tc << " 1\n";
        else    cout << "#" << tc << " 0\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}