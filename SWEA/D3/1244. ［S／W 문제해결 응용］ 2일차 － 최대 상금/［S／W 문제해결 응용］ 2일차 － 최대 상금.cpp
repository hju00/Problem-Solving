/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// float b, c;
// double d, e, f;
// char g;
// char var[256];
// long long AB;
// cin >> a;                            // int 변수 1개 입력받는 예제
// cin >> b >> c;                       // float 변수 2개 입력받는 예제 
// cin >> d >> e >> f;                  // double 변수 3개 입력받는 예제
// cin >> g;                            // char 변수 1개 입력받는 예제
// cin >> var;                          // 문자열 1개 입력받는 예제
// cin >> AB;                           // long long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// float b = 1.0, c = 2.0;               
// double d = 3.0, e = 0.0; f = 1.0;
// char g = &apos;b&apos;;
// char var[256] = &quot;ABCDEFG&quot;;
// long long AB = 12345678901234567L;
// cout << a;                           // int 변수 1개 출력하는 예제
// cout << b << &quot; &quot; << c;               // float 변수 2개 출력하는 예제
// cout << d << &quot; &quot; << e << &quot; &quot; << f;   // double 변수 3개 출력하는 예제
// cout << g;                           // char 변수 1개 출력하는 예제
// cout << var;                         // 문자열 1개 출력하는 예제
// cout << AB;                          // long long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int swap_count, result;
string arr;

void dfs(int cnt, int index)
{
    if(cnt <= 0)
    {
        int current_value = stoi(arr);
        result = max(result, current_value);
        return;
    }
    for(int i = index; i < arr.size(); i++)
    {
        for(int j = i + 1; j < arr.size(); j++)
        {
            if(arr[i] <= arr[j]){
            	swap(arr[i], arr[j]);
            	dfs(cnt - 1, i);
            	swap(arr[i], arr[j]);
            }
            if(i == arr.size() - 2 && j == arr.size() - 1){
            	swap(arr[i], arr[j]);
                dfs(cnt - 1, i);
                swap(arr[i], arr[j]);
            }
        }
    }
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        cin >> arr >> swap_count;
        result = 0;
        
        dfs(swap_count, 0);
        
        cout << "#" << test_case << " " << result << endl; 
        
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}