#include<iostream>

using namespace std;

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1 , 0, -1, 0 };

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N;
        cin >> N;
        int arr[10][10] = { 0, };
        int p = 1, d = 0;
        int cx = 0, cy = 0;
        
        while( p <= N * N)
        {
            arr[cx][cy] = p++;
            int nx = cx + dx[d % 4];
            int ny = cy + dy[d % 4];
            
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] != 0)	{
                d++;
                cx += dx[d % 4];
                cy += dy[d % 4];
            }
			else{
                cx = nx;
                cy = ny;
            }
        }
        
        
        cout << "#" << test_case << endl;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                cout << arr[i][j] << " ";
            }
            cout << "" << endl;
        }
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}