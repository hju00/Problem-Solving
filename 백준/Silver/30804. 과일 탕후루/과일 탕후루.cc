#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

int main() {
	
	int N;	// 1 <= N <= 200,000
	cin >> N;

	vector<int> fruits(N, 0);
	for(int i = 0; i < N; i++)	cin >> fruits[i];

	unordered_map<int, int> cnt;
	int left = 0, right = 0, max_len = 0;

	while(right < N)
	{
		cnt[fruits[right]]++;

		while(cnt.size() > 2)	{
			cnt[fruits[left]]--;
			if(cnt[fruits[left]] == 0)	cnt.erase(fruits[left]);
			left++;
		}

		max_len = max(max_len, right - left + 1);
		right++;
	}

	cout << max_len << '\n';

	return 0;
}