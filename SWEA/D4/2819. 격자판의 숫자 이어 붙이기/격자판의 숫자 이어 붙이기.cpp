#include<iostream>
#include<set>
#include<string>
#define MAX 7;

using namespace std;

int board[4][4];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
set<string> unique_numbers;

void dfs(int x, int y, string num)
{
    if(num.length() == 7){
        unique_numbers.insert(num);
        return;
    }
    for(int i = 0; i < 4; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
            dfs(nx, ny, num + to_string(board[nx][ny]));
        }
    }
    
}

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        unique_numbers.clear();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                cin >> board[i][j];
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                dfs(i, j,  to_string(board[i][j]));
        
        cout << "#" << test_case << " " << unique_numbers.size() << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}