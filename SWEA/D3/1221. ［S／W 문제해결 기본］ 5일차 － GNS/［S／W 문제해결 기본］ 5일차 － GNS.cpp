#include<iostream>
#include<string>
#include<vector>
using namespace std;

string numList[10] = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

	int test_case;
	int T;
	
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        string tc, temp;
        int N;  // 100 ≤ N ≤ 10000
        vector<int> numCount(10, 0);

        cin >> tc >> N;

        while(N--)
        {
            cin >> temp;

            for(int i = 0; i < 10; i++) {
                if(temp == numList[i])  numCount[i]++;
            }
        }

        cout << tc << "\n";
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < numCount[i]; j++)    {
                cout << numList[i] << " ";
            }
        }

        cout << "\n";

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}