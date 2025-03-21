#include <iostream>
using namespace std;

long long modPow(long long A, long long B, long long C) {
    if (B == 0) return 1;  // A^0 = 1
    long long half = modPow(A, B / 2, C) % C;
    half = (half * half) % C;  // 중간 연산에서 C로 나누어 메모리 초과 방지
    if (B % 2 == 1) half = (half * A) % C;  // B가 홀수면 추가 곱셈
    return half;
}

int main() {
    long long A, B, C;
    cin >> A >> B >> C;
    cout << modPow(A, B, C) << '\n';
    return 0;
}