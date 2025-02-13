#include<iostream>
#include<vector>
#include<cstdio>
#include<queue>

using namespace std;

int n, m;
vector<vector<int>> map;
int dy[4] = {1, 0, -1, 0};
int dx[4] = {0, 1, 0, -1};

vector<vector<int>> bfs(pair<int, int> s)
{
    vector<vector<int>> a(n, vector<int> (m, -1));
    queue<pair<int, int>> q;
    a[s.first][s.second] = 0;
    q.push(s);

    while(!q.empty())
    {
        int cur_y = q.front().first;
        int cur_x = q.front().second;
        q.pop();

        for(int i = 0; i < 4; i++)
        {
            int ny = cur_y + dy[i];
            int nx = cur_x + dx[i];

            if(ny < 0 || ny >= n || nx < 0 || nx >= m)  // 지도 이탈
                continue;
            if(map[ny][nx] == 0)  // 갈 수 없는 곳
                continue;
            if(a[ny][nx] != -1)  // 이미 방문한 곳
                continue;

            a[ny][nx] = a[cur_y][cur_x] + 1;
            q.push({ny, nx});
        }
    }

    return a;
}

int main()
{
    cin >> n >> m;
    map.assign(n, vector<int>(m));  // 입력 크기에 맞게 초기화

    pair<int, int> start = {-1, -1};
    int temp;

    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            scanf("%d", &temp);
            map[i][j] = temp;
            if(temp == 2)
                start = {i, j};
        }
    }

    vector<vector<int>> answer = bfs(start);

    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(map[i][j] == 0)
                printf("0 ");
            else
                printf("%d ", answer[i][j]);
        }
        printf("\n");
    }

    return 0;
}
