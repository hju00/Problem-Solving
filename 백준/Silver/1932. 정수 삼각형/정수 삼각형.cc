#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N;	// 1 ≤ n ≤ 500
    cin >> N;
    
    vector<vector<int>> triangle(N, vector<int>(N, 0));

    for (int i = 0; i < N; i++)
        for (int j = 0; j <= i; j++)
            cin >> triangle[i][j];


    for (int i = 1; i < N; i++) {
        for (int j = 0; j <= i; j++) {
            if (j == 0)  // 왼쪽 끝
                triangle[i][j] += triangle[i-1][j];
            else if (j == i)  // 오른쪽 끝
                triangle[i][j] += triangle[i-1][j-1];
            else  // 가운데 값들
                triangle[i][j] += max(triangle[i-1][j-1], triangle[i-1][j]);
        }
    }

    // 마지막 줄에서 최대값 찾기
    cout << *max_element(triangle[N-1].begin(), triangle[N-1].end()) << '\n';

    return 0;
}