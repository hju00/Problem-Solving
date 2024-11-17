#include<iostream>
#include<algorithm>

using namespace std;

int N, L;
int T[20] = { 0 };
int K[20] = { 0 };
int max_score = 0;
void dfs(int idx, int score, int cal)
{
	if(cal > L) return;
    if(idx == N){
        max_score = max(score, max_score);
        return;
    }
    dfs(idx + 1, score + T[idx], cal + K[idx]);
    dfs(idx + 1, score, cal);
}

int main(int argc, char** argv)
{
	int test_case;
	int Tc;

	cin>>Tc;

	for(test_case = 1; test_case <= Tc; ++test_case)
	{   
        max_score = 0;
        cin >> N >> L;
       
        for(int i = 0; i < N; i++)
			cin >> T[i] >> K[i];

        dfs(0, 0, 0);
        cout << "#" << test_case << " " << max_score << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}