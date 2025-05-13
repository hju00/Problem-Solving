#include<iostream>

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
        int board[9][9];
        int result = 1;

        for(int i = 0; i < 9; i++)  {
            for(int j = 0; j < 9; j++)  {
                cin >> board[i][j];
            }
        }

        // 가로 확인
        for(int i = 0; i < 9; i++)  {
            int sum = 0;
            for(int j = 0; j < 9; j++)  {
                sum += board[i][j];
            }
            if(sum != 45)   result = 0;
        }

        // 세로 확인
        for(int j = 0; j < 9; j++)  {
            int sum = 0;
            for(int i = 0; i < 9; i++)  {
                sum += board[i][j];
            }
            if(sum != 45)   result = 0;
        }

        // 3 X 3 확인
        int sum = 0;
        for(int i = 0; i < 3; i++)  {
            for(int j = 0; j < 3; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 3; i < 6; i++)  {
            for(int j = 0; j < 3; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 6; i < 9; i++)  {
            for(int j = 0; j < 3; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 0; i < 3; i++)  {
            for(int j = 3; j < 6; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 3; i < 6; i++)  {
            for(int j = 3; j < 6; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;


        sum = 0;
        for(int i = 6; i < 9; i++)  {
            for(int j = 3; j < 6; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 0; i < 3; i++)  {
            for(int j = 6; j < 9; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 3; i < 6; i++)  {
            for(int j = 6; j < 9; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;

        sum = 0;
        for(int i = 6; i < 9; i++)  { 
            for(int j = 6; j < 9; j++)  {
                sum += board[i][j];
            }
        }
        if(sum != 45)   result = 0;


        cout << "#" << test_case << " " << result << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}