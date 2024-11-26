#include<iostream>
#include<vector>
using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n, a, b;
    cin >> n;
    vector<pair<int, int>> peoples;
    int rank[201][201];
    // memset으로 배열을 초기화 할 경우 0으로 초기화할 때만 제대로 작동
    // 1로 초기화 해야하기 때문에 fill 사용
    fill(&rank[0][0], &rank[200][201], 1);

    for(int i = 0; i < n; i++){
        cin >> a >> b;
        peoples.push_back(make_pair(a, b));

        for(int x = 0; x < a; x++){
            for(int y = 0; y < b; y++){
                rank[x][y]++;
            }
        }

    }

    for(auto i : peoples)
        cout << rank[i.first][i.second] << ' ';

    return 0;
}
