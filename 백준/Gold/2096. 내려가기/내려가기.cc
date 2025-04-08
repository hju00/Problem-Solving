#include <iostream>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);

	int N;
	cin >> N;

	int prev_max[3] = {0}, prev_min[3] = {0}; // 이전 줄
	int cur_max[3] = {0}, cur_min[3] = {0};   // 현재 줄

	for (int i = 0; i < N; i++) {
		int a, b, c;
		cin >> a >> b >> c;

		if (i == 0) {
			prev_max[0] = prev_min[0] = a;
			prev_max[1] = prev_min[1] = b;
			prev_max[2] = prev_min[2] = c;
		}
		else {
			cur_max[0] = max(prev_max[0], prev_max[1]) + a;
			cur_max[1] = max({prev_max[0], prev_max[1], prev_max[2]}) + b;
			cur_max[2] = max(prev_max[1], prev_max[2]) + c;

			cur_min[0] = min(prev_min[0], prev_min[1]) + a;
			cur_min[1] = min({prev_min[0], prev_min[1], prev_min[2]}) + b;
			cur_min[2] = min(prev_min[1], prev_min[2]) + c;

			// 값 교체
			for (int j = 0; j < 3; j++) {
				prev_max[j] = cur_max[j];
				prev_min[j] = cur_min[j];
			}
		}
	}

	cout << *max_element(prev_max, prev_max + 3) << " ";
	cout << *min_element(prev_min, prev_min + 3) << '\n';

	return 0;
}