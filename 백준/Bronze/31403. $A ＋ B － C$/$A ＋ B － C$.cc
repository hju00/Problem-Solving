#include <iostream>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int a, b, c;
    cin >> a >> b >> c;

    int t = 1, temp = b;
    while(temp / t > 0)
        t *= 10;

    cout << a + b - c << '\n';
    cout << a * t + b - c << '\n';
    return 0;
}
