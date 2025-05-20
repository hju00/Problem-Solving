#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

string grade[10] = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

int main(int argc, char** argv)
{
	int test_case;
	int T;
	
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N, K;   //  (10 ≤ N ≤ 100), (1 ≤ K ≤ N)
        vector<pair<double, int>> v;
        cin >> N >> K;

        for(int i = 1; i <= N; i++)  {
            double a, b, c;
            double total;
            cin >> a >> b >> c;
            total = 0.35 * a + 0.45 * b + 0.2 * c;
            v.push_back({total, i});
        }

        sort(v.begin(), v.end(), greater<>());

        for(int i = 0; i < N; i++)  
            if(v[i].second == K)
                cout << "#" << test_case << " " << grade[i / (N / 10)] << "\n";


	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}