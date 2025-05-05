#include <iostream>
#include <algorithm>
using namespace std;

int n;
int wine[10000];
int dp[10000];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n;
    for (int i = 0; i < n; i++) cin >> wine[i];

    if (n == 1) {
        cout << wine[0] << "\n";
        return 0;
    }
	else if(n == 2)	{
		cout << wine[0] + wine[1] << "\n";
		return 0;
	}

    dp[0] = wine[0];
    dp[1] = wine[0] + wine[1];
    dp[2] = max({dp[1], wine[0] + wine[2], wine[1] + wine[2]});

    for (int i = 3; i < n; i++) {
        dp[i] = max({
            dp[i - 1],                         // i번째 안 마심
            dp[i - 2] + wine[i],               // i번째만 마심
            dp[i - 3] + wine[i - 1] + wine[i]  // i, i-1번째 마심
        });
    }

    cout << dp[n - 1] << "\n";
    return 0;
}