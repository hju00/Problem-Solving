#include<iostream>
#include<cstdio>
#include<algorithm>
#include<queue>
#define INT_MAX 1000000000
using namespace std;

int N;
int road[100][100];
int dist[100][100];
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };


int bfs(pair<int, int> p)
{
    queue<pair<int, int>> q;
    
    q.push(p);
    dist[p.first][p.second] = road[p.first][p.second];
    
    while(!q.empty())
    {
        pair<int, int> current_p = q.front();
        q.pop();
        
        for(int i = 0; i < 4; i++){
            int nx = current_p.first + dx[i];
            int ny = current_p.second + dy[i];
            
            if(nx >= 0 && ny >= 0 && nx< N && ny < N)
            {
                int new_cost = dist[current_p.first][current_p.second] + road[nx][ny];
                if(new_cost < dist[nx][ny])
                {
                    dist[nx][ny] = new_cost;
                    q.push(make_pair(nx, ny));
                }
            }
        }
    }
    
    return dist[ N - 1 ][ N - 1 ];
}

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        cin >> N;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
            	scanf("%1d", &road[i][j]);
                dist[i][j] = INT_MAX;
            }
        }
        
        cout << "#" << test_case << " " << bfs(make_pair( 0, 0 )) << endl;
        
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}