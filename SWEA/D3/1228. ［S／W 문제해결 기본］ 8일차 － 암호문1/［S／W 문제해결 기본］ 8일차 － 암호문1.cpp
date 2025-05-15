#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N, C; // ( 10 ≤ N ≤ 20 의 정수), ( 5 ≤ c ≤ 10 의 정수)
        cin >> N;

        vector<int> v;

        while(N--)  {
            int temp;
            cin >> temp;
            v.push_back(temp);
        }

        cin >> C;

        while(C--)
        {
            char Or;
            int x, y;
            cin >> Or >> x >> y;

            vector<int> inserted;
            while(y--) {
                int temp;
                cin >> temp;
                inserted.push_back(temp);
            }

            reverse(inserted.begin(), inserted.end());
            for(auto i : inserted)  v.begin() = v.insert(v.begin() + x, i);
        }

        cout << "#" << test_case << " ";
        for(int i = 0; i < 10; i++) cout << v[i] << " ";
        cout << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}