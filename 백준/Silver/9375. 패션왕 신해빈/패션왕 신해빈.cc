#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int T;	// 최대 100
	cin >> T;

	for(int test_case = 1; test_case <= T; test_case++)
	{
		int n;	// 0 ≤ n ≤ 30 
		string c_name, c_category;
		vector<string> clothes;
		cin >> n;
		if(n == 0) {
			cout << "0\n";
			continue;
		}
		
		for(int i = 0; i < n; i++)	{
			cin >> c_name >> c_category;
			clothes.push_back(c_category);
		}
		
		sort(clothes.begin(), clothes.end());
		vector<int> v;
		int cnt = 1;
		string last_cloth = clothes[0];

		for(int i = 1; i < clothes.size(); i++)	{
			if(clothes[i] == last_cloth) cnt++;
			else {
				v.push_back(cnt);
				last_cloth = clothes[i];
				cnt = 1;
			}
		}

		v.push_back(cnt);

		if(v.size() == 1)	cout << *v.begin() << '\n';
		else {
			int ans = 1;
			for(int i : v) {
				ans *= i + 1;
			}
			cout << ans - 1 << '\n';
		}

	}
	return 0;
}