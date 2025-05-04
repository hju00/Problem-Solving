#include <iostream>
using namespace std;

int main() {
    long long A, B;
    cin >> A >> B;

    int cnt = 1;
    while (B > A) {
        if (B % 2 == 0) {
            B /= 2;
        } else if (B % 10 == 1) {
            B /= 10;
        } else {
            break;
        }
        cnt++;
    }

    if (B == A) cout << cnt << "\n";
    else cout << "-1\n";

    return 0;
}