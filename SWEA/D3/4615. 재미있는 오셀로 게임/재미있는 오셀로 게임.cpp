#include<iostream>
#include<vector>
using namespace std;

// 1 -> 흑돌, 2 -> 백돌
int N, M;
vector<vector<int>> v;
int dy[8] = {1, -1, 0, 0, 1, -1, 1, -1};
int dx[8] = {0, 0, 1, -1, -1, 1, 1, -1};

bool isValid(int y, int x) { return y >= 0 && y < N && x >= 0 && x < N; }

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T;
	cin >> T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        cin >> N >> M;
        v.assign(N, vector<int>(N, 0));

        v[N/2][N/2] = 2;
        v[N/2-1][N/2-1] = 2;
        v[N/2][N/2-1] = 1;
        v[N/2-1][N/2] = 1;

        while(M--) {
            int x, y, color, enemy;
            cin >> x >> y >> color;
            x--; y--;
            v[y][x] = color;

            enemy = (color == 1 ? 2 : 1);

            for(int d = 0; d < 8; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                vector<pair<int, int>> to_flip;

                while(isValid(ny, nx) && v[ny][nx] == enemy) {
                    to_flip.push_back({ny, nx});
                    ny += dy[d];
                    nx += dx[d];
                }

                if(isValid(ny, nx) && v[ny][nx] == color) {
                    for(auto& p : to_flip)
                        v[p.first][p.second] = color;
                }
            }
        }

        int bc = 0, wc = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                if(v[i][j] == 1) bc++;
                else if(v[i][j] == 2) wc++;
            }

        cout << "#" << test_case << " " << bc << " " << wc << "\n";
	}

	return 0;
}