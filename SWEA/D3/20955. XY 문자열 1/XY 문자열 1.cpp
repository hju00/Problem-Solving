#include <iostream>
#include <string>
#include <algorithm>
 
using namespace std;
 
int main(int argc, char** argv) {
    int test_case;
    int T;
 
    cin>>T;
 
    for(test_case = 1; test_case <= T; ++test_case)
    {
        string s, e;
        bool ans;
        cin >> s;
        cin >> e;
        for (int i = e.length() - 1; i >= 0; i--) {
            if (e[i] == 'Y') {
                //e = e - 'Y';
                e.erase(i, 1);
                reverse(e.begin(), e.end());
            }
            else if (e[i] == 'X') {
                //e = e - 'X';
                e.erase(i, 1);
            }
            if (e == s) {
                ans = true;
                break;
            }
            else if (e.length() < s.length()) {
                ans = false;
                break;
            }
        }
         
        if (ans) cout << '#' << test_case << " Yes" << endl;
        else cout << '#' << test_case << " No" << endl;
    }
    return 0;//정상종료시 반드시 0을 리턴해야합니다.
}