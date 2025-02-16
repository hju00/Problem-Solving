#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

// R(뒤집기) 연산은 방향 플래그로 처리
void apply_R(bool &is_reversed) {
    is_reversed = !is_reversed;
}

// D(버리기) 연산
bool apply_D(deque<int> &dq, bool is_reversed) {
    if (dq.empty()) return false;
    
    if (is_reversed)
        dq.pop_back();
    else
        dq.pop_front();
    
    return true;
}

// 문자열을 쉼표 기준으로 숫자 벡터로 변환하는 함수
deque<int> parseArray(string s) {
    deque<int> result;
    
    // 대괄호 제거 (앞뒤 한 글자씩 삭제)
    s = s.substr(1, s.size() - 2);
    
    if (s.empty()) return result; // 빈 배열 예외 처리

    stringstream ss(s);
    string num;

    // 쉼표(,)를 기준으로 문자열을 나누어 숫자로 변환
    while (getline(ss, num, ',')) {
        result.push_back(stoi(num));
    }

    return result;
}


int main() {
    int test_case;
    cin >> test_case;

    while (test_case--) {
        string func;
        int n;
        string arr_str;
        
        cin >> func;
        cin >> n;
        cin >> arr_str;
        
        deque<int> dq = parseArray(arr_str);
        bool is_reversed = false, is_error = false;

        for (char c : func) {
            if (c == 'R') {
                apply_R(is_reversed);
            } else if (c == 'D') {
                if (!apply_D(dq, is_reversed)) {
                    is_error = true;
                    break;
                }
            }
        }

        if (is_error) {
            cout << "error\n";
        } else {
            cout << "[";
            if (is_reversed) reverse(dq.begin(), dq.end());
            
            for (size_t i = 0; i < dq.size(); i++) {
                if (i > 0) cout << ",";
                cout << dq[i];
            }
            cout << "]\n";
        }
    }

    return 0;
}
