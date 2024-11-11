#include<iostream>
#include<algorithm>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;

	for(test_case = 1; test_case <= 10; ++test_case)
	{
        cin >> T;

        int arr[100][100];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++)
                cin >> arr[i][j];
        }
        
        int ans = 0, sum = 0;
        
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                sum += arr[i][j];
            }
            ans = max(ans, sum);
            sum = 0;
        }
        
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                sum += arr[j][i];
            }
            ans = max(ans, sum);
            sum = 0;
        }
 		      
        for(int i = 0; i < 100; i++){
            sum += arr[i][i];
        }
        
        ans = max(ans, sum);
        sum = 0;
        
        for(int i = 0; i < 100; i++){
        	sum += arr[99 - i][i];
        }
        
        ans = max(ans, sum);

        cout << "#" << test_case << " " << ans << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}