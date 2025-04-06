#include <iostream>
#include <vector>
using namespace std;

int N;

int main() {
    cin >> N;
    vector<vector<int>> v(N, vector<int>(N));

    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            cin >> v[i][j];

    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (v[i][k] && v[k][j])
                    v[i][j] = 1;
            }
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++)
            cout << v[i][j] << ' ';
        cout << '\n';
    }

    return 0;
}