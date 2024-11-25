#include <iostream>
#define r 31
#define M 1234567891
using namespace std;

int main() {
    
    int L;
    string s;
    long long ans = 0;
    cin >> L;
    cin >> s;
    int r_power = 1;

    for(int i = 0; i < L; i++){
        long long temp = ((s[i] - 'a' + 1) * r_power) % M;
        r_power *= r;
        r_power %= M;
        ans += temp;
    }

    cout << ans << endl;

    return 0;
}
