// 1은 N극 성질을 가지는 자성체를 2는 S극 성질을 가지는 자성체를 의미하며 
// 테이블의 윗부분에 N극이 아래부분에 S극이 위치한다고 가정한다.

#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int back;
        cin >> back;

        int table[100][100];

        for(int i = 0; i < 100; i++)    {
            for(int j = 0; j < 100; j++)    {
                cin >> table[i][j];
            }
        }

        int deadlock = 0;

        for(int j = 0; j < 100; j++)    {
            bool exist_n = false;

            for(int i = 0; i < 100; i++)    {

                if(table[i][j] == 1)    exist_n = true;
                else if(table[i][j] == 2)   {

                    if(exist_n) {   
                        deadlock++;
                        exist_n = false;
                    }
                    else    continue;
                }
                else if(table[i][j] == 0)    continue;
            }
        }

        cout << "#" << test_case << " " << deadlock << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}