#include<iostream>
#include<vector>
using namespace std;

int H, W, N;    // (2 ≤ H, W ≤ 20), (0 < N ≤ 100)
string commmand;
vector<vector<char>> map;
char tank[4] = {'<', '>', '^', 'v'};

bool isValid(int y, int x)  {   return y >= 0 && y < H && x >= 0 && x < W;  }

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{   
        pair<int, int> cur;
        cin >> H >> W;

        map.assign(H, vector<char>(W));
        for(int i = 0; i < H; i++)  {
            for(int j = 0; j < W; j++)  {
                cin >> map[i][j];
                for(char c : tank)  
                    if(map[i][j] == c)  cur = {i, j};
            }
        }

        cin >> N >> commmand;

        for(char c : commmand)
        {
            int cy = cur.first;
            int cx = cur.second;

            switch (c)
            {
            case 'U':
                if(isValid(cy - 1, cx) && map[cy - 1][cx] == '.')   {
                    map[cy - 1][cx] = '^';
                    map[cy][cx] = '.';
                    cur = {cy - 1, cx};
                }
                else    map[cy][cx] = '^';
                break;

            case 'D':
                if(isValid(cy + 1, cx) && map[cy + 1][cx] == '.')   {
                    map[cy + 1][cx] = 'v';
                    map[cy][cx] = '.';
                    cur = {cy + 1, cx};
                }
                else    map[cy][cx] = 'v';
                break;

            case 'L':
                if(isValid(cy, cx - 1) && map[cy][cx - 1] == '.')   {
                    map[cy][cx - 1] = '<';
                    map[cy][cx] = '.';
                    cur = {cy, cx - 1};
                }
                else    map[cy][cx] = '<';
                break;

            case 'R':
                if(isValid(cy, cx + 1) && map[cy][cx + 1] == '.')   {
                    map[cy][cx + 1] = '>';
                    map[cy][cx] = '.';
                    cur = {cy, cx + 1};
                }
                else    map[cy][cx] = '>';
                break;

            case 'S':
                if(map[cy][cx] == '^')  {
                    for(int i = 1; cy - i >= 0; i++)    {
                        if(map[cy - i][cx] == '*')  {
                            map[cy - i][cx] = '.';
                            break;
                        }
                        else if(map[cy - i][cx] == '#')     break;
                    }
                }
                else if(map[cy][cx] == 'v') {
                    for(int i = 1; cy + i < H; i++)    {
                        if(map[cy + i][cx] == '*')  {
                            map[cy + i][cx] = '.';
                            break;
                        }
                        else if(map[cy + i][cx] == '#')     break;
                    }
                }
                else if(map[cy][cx] == '<') {
                    for(int i = 1; cx - i >= 0; i++)    {
                        if(map[cy][cx - i] == '*')  {
                            map[cy][cx - i] = '.';
                            break;
                        }
                        else if(map[cy][cx - i] == '#')     break;
                    }
                }
                else if(map[cy][cx] == '>') {
                    for(int i = 1; cx + i < W; i++)    {
                        if(map[cy][cx + i] == '*')  {
                            map[cy][cx + i] = '.';
                            break;
                        }
                        else if(map[cy][cx + i] == '#')     break;
                    }
                }
                break;
            }
        }

        cout << "#" << test_case << " ";
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++)  {
                cout << map[i][j];
            }
            cout << "\n";
        }
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}