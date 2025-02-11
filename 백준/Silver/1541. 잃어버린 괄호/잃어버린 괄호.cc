#include <iostream>
#include <string>

using namespace std;

int main() {
    string s;
    getline(cin, s);
    
    int total = 0;
    string t = "";
    bool exist_minus = false;

    for (int i = 0; i <= s.length(); i++) {  // `<=` 사용하여 마지막 숫자도 처리
        if (i == s.length() || s[i] == '+' || s[i] == '-') {
            if (!exist_minus) 
                total += stoi(t);
            else 
                total -= stoi(t);
                
            t = ""; // `t` 초기화
            
            if (i < s.length() && s[i] == '-')  // `-`가 나오면 이후 숫자는 무조건 뺌
                exist_minus = true;
        } else
            t += s[i]; // 숫자 문자 추가
    }

    cout << total << endl;
    return 0;
}
