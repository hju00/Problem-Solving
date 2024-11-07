#include<iostream>
#include<algorithm>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;

	for(test_case = 1; test_case <= 10; ++test_case)
	{
        int dump_cnt, temp;
        int arr[101] = { 0, };
        
        cin >> dump_cnt;
        
        for(int i = 0; i < 100; i++){
        	cin >> temp;
            arr[temp]++;
        }
        
        int i = 1, j = 100;
        
        while(dump_cnt)
        {
            while( arr[i] == 0 )	i++;
            while( arr[j] == 0 )	j--;
            if(	j - i <= 1 )		break;
            arr[j]--;
            arr[j - 1]++;
            arr[i]--;
            arr[i + 1]++;
            dump_cnt--;
        }
        while( arr[i] == 0 )	i++;
        while( arr[j] == 0 )	j--;
        
        cout << "#" << test_case << " " << j - i << endl;
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}