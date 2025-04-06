#include <iostream>
#include <string>
#include <unordered_map>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N, M;
	string web, pw;
	unordered_map<string, string> m;

	cin >> N >> M;

	while (N--) {
		cin >> web >> pw;
		m[web] = pw;
	}

	while (M--) {
		cin >> web; // 이거 빠지면 안 돼요!
		cout << m[web] << '\n';
	}

	return 0;
}