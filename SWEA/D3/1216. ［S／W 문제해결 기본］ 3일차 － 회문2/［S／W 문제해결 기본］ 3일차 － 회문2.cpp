#include<iostream>
#include<algorithm>
using namespace std;

char board[100][100];

bool is_Palin_Row(int y, int x, int l) 
{   
    for(int i = 0; i < l / 2; i++)  {
        if(board[y][x + i] != board[y][x + l - 1 - i])  return false;
    }
    return true;
}

bool is_Palin_Col(int y, int x, int l) 
{   
    for(int i = 0; i < l / 2; i++)  {
        if(board[y + i][x] != board[y + l - 1 - i][x])  return false;
    }
    return true;
}

int main(int argc, char** argv)
{
	ios::sync_with_stdio(false);
    cin.tie(0);

    int test_case;
	int T = 10;
    
	for(test_case = 1; test_case <= T; ++test_case)
	{
        int tc, ans = 0;
        cin >> tc;

        for(int i = 0; i < 100; i++)
            for(int j = 0; j < 100; j++)
                cin >> board[i][j];

        bool found = false;
        int len = 100;

        for(; len >= 1; len--) {

            for(int i = 0; i < 100 - len + 1; i++)  {
                for(int j = 0; j < 100 - len + 1; j++)  {

                    if(is_Palin_Row(i, j, len) || is_Palin_Col(i, j, len))  found = true;

                }

                if(found)   break;
            }

            if(found)   break;
        }
        
        cout << "#" << tc << " " << len << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}