#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N, M;
vector<vector<int>> paris;

int kill(int x, int y)
{
    int sum = 0;
    for(int i = x; i < x + M; i++){
        for(int j = y; j < y + M; j++){
            sum += paris[i][j];
        }
    }

    return sum;
}

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int max_kill = 0;
        cin >> N >> M;

        paris.assign(N, vector<int>(N));

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                cin >> paris[i][j];
        
        for(int i = 0; i < N - M + 1; i++)
            for(int j = 0; j < N - M + 1; j++)
                max_kill = max(max_kill, kill(i, j));

        cout << "#" << test_case << " " << max_kill << '\n';
        
	}

	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}