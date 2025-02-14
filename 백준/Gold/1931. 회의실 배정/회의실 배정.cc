#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdio>

using namespace std;

int main() {

	int N, start, end;
	vector<pair<int, int>> meetings;

	cin >> N;
	for(int i = 0; i < N; i++)
	{
		scanf("%d %d", &start, &end);
		meetings.push_back({end, start});
	}

	// 끝나는 시간을 오름차순으로 정렬
	sort(meetings.begin(), meetings.end());
	
	int cnt = 0;
	int last_end = 0;

	for(auto &meeting : meetings)
	{
		int cur_end = meeting.first;
		int cur_start = meeting.second;

		if(cur_start >= last_end)
		{
			cnt++;
			last_end = cur_end;
		}
	}

	cout << cnt << endl;

	return 0;
}