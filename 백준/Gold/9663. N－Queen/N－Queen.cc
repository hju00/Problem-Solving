#include<iostream>
#include<vector>

using namespace std;

int N, answer;
int queen[15];

bool check(int y)
{
    for(int i = 0; i < y; i++)
        if( queen[i] == queen[y] || abs( i - y ) == abs( queen[i] - queen[y] ))	return false;
    
    return true;
}

void dfs(int index)
{
    if(index == N)	answer++;
    else
    {
        for(int i = 0; i < N; i++)
        {
            queen[index] = i;
            if(check(index))	dfs(index + 1);
        }
    }
}

int main(int argc, char** argv)
{

        cin >> N;
        
        for(int i = 0; i < N; i++){
			queen[i] = 0;
        }
        
        answer = 0;
        dfs(0);
        cout << answer << endl; 

	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}