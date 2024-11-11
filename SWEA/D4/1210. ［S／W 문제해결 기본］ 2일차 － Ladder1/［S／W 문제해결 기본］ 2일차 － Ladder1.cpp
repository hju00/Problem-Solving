#include<iostream>

using namespace std;

int maze[100][100];

int reverse(pair<int, int> s)
{
    int x = s.first;
    int y = s.second;
    
    while(x != 0)
    {
		if(maze[x][y-1] == 1 && y - 1 >= 0){
            while(maze[x][y-1] == 1 && y - 1 >= 0) {
            	y--;
        	}
            x--;
        }
        else if(maze[x][y+1] == 1 && y + 1 < 100){
			while(maze[x][y+1] == 1 && y + 1 < 100) {
                y++;
            }
            x--;
        }
        else x--;
    }
    return y;
}

int main(int argc, char** argv)
{
	int test_case;
	int T;

	for(test_case = 1; test_case <= 10; ++test_case)
	{
        cin >> T;
        pair<int, int> start;
        
		for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                cin >> maze[i][j];
                if(maze[i][j] == 2)	start = make_pair(i, j);
            }
        }
        
        cout << "#" << test_case << " " << reverse(start) << endl;
        
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}