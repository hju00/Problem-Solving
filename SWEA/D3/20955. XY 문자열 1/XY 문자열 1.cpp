#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

string S, E;

bool is_ans() {
    // E의 길이가 S의 길이보다 길면 계속 변환 시도
    while (S.length() < E.length()) {
        if (E.back() == 'X') {
            E.pop_back();  // 끝이 'X'이면 마지막 문자 제거
        } else {
            E.pop_back();
            reverse(E.begin(), E.end());  // 끝이 'Y'이면 문자 제거 후 뒤집기
        }
    }
    return S == E;  // 최종적으로 S와 E가 동일한지 확인
}

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; ++test_case) {
        cin >> S >> E;
        if (is_ans()) cout << "#" << test_case << " Yes" << endl;
        else          cout << "#" << test_case << " No" << endl;
    }
    return 0;
}