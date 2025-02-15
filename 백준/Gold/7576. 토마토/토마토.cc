#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int m, n;
vector<vector<int>> tomatoes;
queue<pair<int, int>> q;
int dy[4] = {1, 0, -1, 0};
int dx[4] = {0, 1, 0, -1};

int ripe_day()
{
    int day = -1; 
    while (!q.empty())
    {
        int q_size = q.size();

        for (int i = 0; i < q_size; i++) // 하루 단위로 퍼지게 함
        {
            int cur_y = q.front().first;
            int cur_x = q.front().second;
            q.pop();

            for (int i = 0; i < 4; i++)
            {
                int ny = cur_y + dy[i];
                int nx = cur_x + dx[i];

                if (ny < 0 || ny >= m || nx < 0 || nx >= n)
                    continue;
                if (tomatoes[ny][nx] != 0) // 익지 않은 토마토만 감염 가능
                    continue;

                tomatoes[ny][nx] = 1;
                q.push({ny, nx});
            }
        }

        day++; // 하루 경과
    }

    // 모든 토마토가 익었는지 확인
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (tomatoes[i][j] == 0)
                return -1;

    return day;
}

int main()
{
    cin >> n >> m;

    tomatoes.assign(m, vector<int>(n));

    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
        {
            cin >> tomatoes[i][j];
            if (tomatoes[i][j] == 1)
                q.push({i, j});
        }

    cout << ripe_day() << endl;
    return 0;
}
