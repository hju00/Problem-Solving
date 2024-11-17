#include<iostream>
#define MAX 10001
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N;
        cin >> N;
        int scores[101];
        
        for(int i = 0; i < N; i++){
            cin >> scores[i];
        }
		
        bool check[MAX] = {false};
        check[0] = true;
        int max_score = 0;
        
        for(int i = 0; i < N; i++){
            for(int j = max_score; j >= 0; j--){
                if(check[j]){
                    check[j + scores[i]] = true;
                }
            }
            max_score += scores[i];
        }
        int answer = 0;
        for(int i = 0; i <= max_score; i++)
            if(check[i])	answer++;
        
        cout << "#" << test_case << " " << answer << endl;
            
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}