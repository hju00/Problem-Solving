#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    if (n == 0) {
        cout << 0 << '\n'; // 예외 처리: 출력 방식은 문제 명세에 맞게 조정
        return 0;
    }

    vector<int> scores(n);
    for (int i = 0; i < n; i++) {
        cin >> scores[i];
    }

    // 점수 정렬
    sort(scores.begin(), scores.end());

    // 제외할 점수 개수 (버림)
    int exclude_count = (int)floor(n * 0.15 + 0.5);

    // 남은 점수들의 합 계산
    double sum = 0;
    for (int i = exclude_count; i < n - exclude_count; i++)
        sum += scores[i];

    // 총 점수 갯수
    int remaining_count = n - 2 * exclude_count;

    // 평균 출력 (반올림)
    cout << (int)floor(sum / remaining_count + 0.5) << '\n';

    return 0;
}
