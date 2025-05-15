#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
using namespace std;

int N, K;   // N은 4의 배수이고 (8 ≤ N ≤ 28) 
char hexa[16] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
vector<int> max_num;

void make_decimal(string st)
{
    for(int i = 0; i < N; i += N / 4)   {

        int temp = 1;
        int num = 0;

        for(int j = N / 4 - 1; j >= 0; j--)  {
            for(int idx = 0; idx < 16; idx++)   {
                if(st[i + j] == hexa[idx])  num += temp * idx;
            }
            temp *= 16;
        }

        max_num.push_back(num);
    }
}

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        string s;
        cin >> N >> K >> s;
        max_num.clear();

        int rotate_cnt = N / 4;

        while(rotate_cnt--)
        {
            make_decimal(s);
            s.append(1, s[0]);  // 맨 뒤에 첫 번째 문자 추가 후
            s.erase(0, 1);      // 첫 번째 문자 제거
        }

        sort(max_num.begin(), max_num.end(), greater<>());
        max_num.erase(unique(max_num.begin(), max_num.end()), max_num.end());
        
        cout << "#" << test_case << " " << max_num[K - 1] << "\n";

        
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}