#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N;
    cin >> N;

    vector<int> v(N);
    for (int i = 0; i < N; i++) cin >> v[i];

    vector<long long> rPSum(N), lPSum(N); // 오른쪽 누적합, 왼쪽 누적합

    rPSum[0] = v[0];
    for (int i = 1; i < N; i++) rPSum[i] = rPSum[i - 1] + v[i];

    lPSum[N - 1] = v[N - 1];
    for (int i = N - 2; i >= 0; i--) lPSum[i] = lPSum[i + 1] + v[i];

    long long res = 0;

    //	벌통이 오른쪽 끝, 벌이 왼쪽 끝 + 중간
    for (int i = 1; i < N - 1; i++) 
        res = max(res, rPSum[N - 1] - v[0] - v[i] + (rPSum[N - 1] - rPSum[i]));

    //	벌통이 왼쪽 끝, 벌이 오른쪽 끝 + 중간
    for (int i = 1; i < N - 1; i++)
        res = max(res, rPSum[N - 1] - v[N - 1] - v[i] + rPSum[i - 1]);

    //	벌이 양 끝, 벌통이 중간
    for (int i = 1; i < N - 1; i++)
        res = max(res, rPSum[i] - v[0] + lPSum[i] - v[N - 1]);

    cout << res << "\n";
    return 0;
}
