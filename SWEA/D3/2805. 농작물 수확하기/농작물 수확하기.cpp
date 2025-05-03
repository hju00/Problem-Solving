#include<iostream>

using namespace std;

int main(int argc, char** argv)
{

    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N;  // N은 1 이상 49 이하의 홀수이다. (1 ≤ N ≤ 49)
        cin >> N;
        
        int farm[49][49];
        int ans = 0;

        for(int i = 0; i < N; i++)  
            for(int j = 0; j < N; j++){
                char score;
                cin >> score;
                farm[i][j] = score - '0';
                ans += farm[i][j];
            }
        
        int half = N / 2;

        if(half == 0)   {
            cout << "#" << test_case << " " << farm[0][0] << "\n";
            continue;
        }

        int start = 0, end = N - 1, temp = half;
        while(start != end)   
        {
            for(int i = 0; i < temp; i++)  {
                ans -= farm[start][i] + farm[start][N - 1 - i] + farm[end][i] + farm[end][N - 1 - i];
            }
            start++;
            end--;
            temp--;
        }

        cout << "#" << test_case << " " << ans << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}