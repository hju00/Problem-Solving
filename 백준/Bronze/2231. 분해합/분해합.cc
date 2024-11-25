#include <iostream>
using namespace std;

int main() {
    
    int N;
    int ans = 0;
    cin >> N;

    for(int i = N; i > 0; i--){
        int p = i;
        int sum = 0;
        while(p > 0){
            sum += p % 10;
            p /= 10;
        }
        if(N == sum + i)    ans = i;
        else continue;
    }
    
    cout << ans << endl;

    return 0;
}
