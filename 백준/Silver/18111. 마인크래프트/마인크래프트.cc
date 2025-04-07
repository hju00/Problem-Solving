#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, B;	// 1 ≤ M, N ≤ 500, 0 ≤ B ≤ 6.4 × 10^7
vector<int> land;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M >> B;
	N *= M;

	land.assign(N, 0);
	for(int i = 0; i < N; i++)	cin >> land[i];
	sort(land.begin(), land.end(), greater<>());

	int ans_time = 1e8, ans_height;

	for(int h = 0; h <= 256; h++)
	{
		int time = 0;
		int inven = B;

		for(int l : land)	{
			int diff = l - h;
			
			if(diff > 0) {
				time += 2 * diff;
				inven += diff;
			}
			else {
				time -= diff;
				inven += diff;
			}
		}

		if(inven >= 0)
			if(time <= ans_time) {
				ans_time = time;
				ans_height = h;
			}
	}
	
	cout << ans_time << ' ' << ans_height << '\n';

	return 0;
}