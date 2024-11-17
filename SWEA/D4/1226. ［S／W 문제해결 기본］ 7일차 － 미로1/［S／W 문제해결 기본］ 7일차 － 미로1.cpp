#include<iostream>
#include<cstdio>
#include<queue>

using namespace std;

int maze[16][16];
bool check[16][16];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
pair<int, int> start_p, end_p;

void bfs(int sx, int sy)
{
    check[sx][sy] = true;
    if(sx == end_p.first && sy == end_p.second) return;
    queue<pair<int, int>> q;
    q.push(make_pair(sx, sy));
    
    while(!q.empty())
    {
        pair<int, int> current = q.front();
        q.pop();
		for(int i = 0; i < 4; i++){
            int nx = current.first + dx[i];
            int ny = current.second + dy[i];
            if(nx >= 0 && ny >= 0 && nx < 16 && ny < 16 && maze[nx][ny] != 1){
                if(!check[nx][ny]){
                	q.push(make_pair(nx, ny));
                    check[nx][ny] = true;
                }
            }
                
        }
    }
}

int main(int argc, char** argv)
{
	int test_case;
	int T;

	

	for(test_case = 1; test_case <= 10; ++test_case)
	{
        cin>>T;
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                scanf("%1d", &maze[i][j]);
                check[i][j] = false;
                if(maze[i][j] == 2)	start_p = make_pair(i, j);
                else if(maze[i][j] == 3) end_p = make_pair(i, j);
            } 
        }
        
        bfs(start_p.first, start_p.second);
        
        if(check[end_p.first][end_p.second])
        	cout << "#" << test_case << " " << 1 << endl;
        else
        	cout << "#" << test_case << " " << 0 << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}