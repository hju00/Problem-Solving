#include <iostream>
#define r 31
#define M 1234567891
using namespace std;

int main() {
    int L;
    string s;
    long long ans = 0;       // 해시 값을 저장할 변수
    long long r_power = 1;   // r^i 값을 계산하며 모듈로 연산 유지

    cin >> L;
    cin >> s;

    for (int i = 0; i < L; i++) {
        // 현재 문자의 해시 값을 계산하고 누적
        ans = (ans + (s[i] - 'a' + 1) * r_power % M) % M;

        // r^i 값을 갱신하며 모듈로 연산 적용
        r_power = (r_power * r) % M;
    }

    cout << ans << endl;
    return 0;
}
