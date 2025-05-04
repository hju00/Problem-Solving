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

        int len, cnt = 0;
        char map[8][8];

        cin >> len;

        for(int i = 0; i < 8; i++) 
            for(int j = 0; j < 8; j++)
                cin >> map[i][j];
        
        // 가로 회문 탐색
        for(int i = 0; i < 8; i++)    {
            for(int j = 0; j < 9 - len; j++)    {
                bool is_palin = true;

                for(int k = 0; k < len / 2; k++)    {
                    if(map[i][j + k] != map[i][j + len - 1 - k])
                        is_palin = false;
                }

                if(is_palin)    cnt++;
            }
        }

        // 세로 회문 탐색
        for(int i = 0; i < 9 - len; i++)    {
            for(int j = 0; j < 8; j++)    {
                bool is_palin = true;

                for(int k = 0; k < len / 2; k++)    {
                    if(map[i + k][j] != map[i + len - 1 - k][j])
                        is_palin = false;
                }

                if(is_palin)    cnt++;
            }
        }
        
        cout << "#" << test_case << " " << cnt << "\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}