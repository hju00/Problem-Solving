#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<int> seq;

void backtrack(int start) {
    if (seq.size() == M) { // M개의 숫자를 골랐다면 출력
        for (int num : seq)
            cout << num << " ";
        cout << '\n';
        return;
    }

    for (int i = start; i <= N; i++) {
        seq.push_back(i);      // 숫자 선택
        backtrack(i + 1);      // 다음 숫자는 i보다 커야 함
        seq.pop_back();        // 백트래킹 (되돌리기)
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
	cout.tie(0);
	
    cin >> N >> M;
    backtrack(1); // 1부터 시작
    return 0;
}
