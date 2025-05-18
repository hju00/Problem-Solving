#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        vector<int> v;
        int N, M;  //  ( 2000 ≤ N ≤ 4000 의 정수), ( 250 ≤ M ≤ 500 의 정수)
        cin >> N;

        while(N--)  {
            int temp;
            cin >> temp;
            v.push_back(temp);
        }

        cin >> M;
        while(M--)  
        {
            char command;
            cin >> command;

            if(command == 'I')  {
                int x, y;
                cin >> x >> y;
                vector<int> t;

                while(y--)  {
                    int temp;
                    cin >> temp;
                    t.push_back(temp);
                }
                reverse(t.begin(), t.end());

                for(int i : t)  v.insert(v.begin() + x, i);

            }
            else if(command == 'D') {
                int x, y;
                cin >> x >> y;

                while(y--)  v.erase(v.begin() + x);
            }
            else if(command == 'A') {
                int y;
                cin >> y;

                while(y--)  {
                    int temp;
                    cin >> temp;
                    v.push_back(temp);
                }
            }
        }

        cout << "#" << test_case << " ";
        for(int i = 0; i < 10; i++)     cout << v[i] << " ";
        cout << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}