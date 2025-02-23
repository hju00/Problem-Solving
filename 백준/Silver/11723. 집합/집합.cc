#include <iostream>
#include <bitset>
#include <string>

using namespace std;

bitset<21> s;  // 1~20까지 숫자 관리

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int M, x;
    string cmd;
    cin >> M;

    while (M--) {
        cin >> cmd;
        if (cmd == "add") {
            cin >> x;
            s.set(x);
        } 
        else if (cmd == "remove") {
            cin >> x;
            s.reset(x);
        } 
        else if (cmd == "check") {
            cin >> x;
            cout << s.test(x) << "\n";
        } 
        else if (cmd == "toggle") {
            cin >> x;
            s.flip(x);
        } 
        else if (cmd == "all") {
            s.set();  // 모든 비트 1로 설정
        } 
        else if (cmd == "empty") {
            s.reset();  // 모든 비트 0으로 설정
        }
    }
    return 0;
}
