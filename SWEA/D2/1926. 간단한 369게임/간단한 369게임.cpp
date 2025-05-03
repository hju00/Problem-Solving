#include<iostream>
#include<string>

using namespace std;

int main(int argc, char** argv)
{
	int N;  // (10 ≤ N ≤ 1,000)
    cin >> N;

    for(int i = 1; i <= N; i++)
    {
        int t = i;
        string ans = "";

        while(t > 0)
        {
            if(t % 10 != 0) {
                if((t % 10) % 3 == 0)   ans += "-";
            }
            t /= 10;
        }

        if(ans != "")   cout << ans << " ";
        else    cout << i << " ";
    }
	

	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}