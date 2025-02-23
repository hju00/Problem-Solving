#include <iostream>
#include <vector>
using namespace std;

int main() {
	
    ios::sync_with_stdio(false);  // C++ 입출력 최적화
    cin.tie(0);                   // 입력 속도 향상
    cout.tie(0);                  // 출력 속도 향상
    
	int N, M, a, b;
	cin >> N >> M;

	vector<int> v(N + 1, 0);
	vector<int> prefix(N + 1, 0); // 누적 합 배열

	for(int i = 1; i <= N; i++) {
		cin >> v[i];
		prefix[i] = prefix[i - 1] + v[i];
	}

	for(int t = 0; t < M; t++) {
		cin >> a >> b;
		cout << prefix[b] - prefix[a - 1] << "\n";  // O(1)
	}

	return 0;
}
