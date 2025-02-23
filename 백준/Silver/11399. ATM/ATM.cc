#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	
	int N;
	int result = 0;
	cin >> N;

	vector<int> times(N);

	for(int i = 0; i < N; i++)
		scanf("%d", &times[i]);

	sort(times.begin(), times.end());

	for(int i = 0; i < N; i++)
		result += times[i] * (N - i);

	cout << result << "\n";

	return 0;
}