#include <iostream>
#include <cstdio>
#include <queue>

using namespace std;

//첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다
int N, M;
int map[101][101];
int visit[101][101];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void input(){
    cin >> N >> M;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            scanf("%1d", &map[i][j]);
        }
    }
}

void bfs(){

    visit[0][0] = 1;

    queue<pair<int, int>> q;
    q.push(make_pair(0, 0));

    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;

        q.pop();

        for(int i = 0; i < 4; i++){
            int next_x = x + dx[i];
            int next_y = y + dy[i];

            if(0 <= next_x && next_x < N && 0 <= next_y && next_y < M){
                if(map[next_x][next_y] == 1 && visit[next_x][next_y] == 0){
                    visit[next_x][next_y] = visit[x][y] + 1;
                    q.push(make_pair(next_x, next_y));
                }
            }
        }
    }
    
}

int main(){
    input();
    bfs();
    
    cout << visit[N - 1][M - 1] << endl;

    return 0;
}