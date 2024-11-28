#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n;     // (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
    long long m;
    cin >> n >> m;

    vector<long long> tree(n);
    long long max_length = 0;

    for(int i = 0; i < n; i++){
        cin >> tree[i];
        max_length = max(max_length, tree[i]);
    }

    long long low = 1, high = max_length, ans = 0;

    while(low <= high)
    {
        long long mid = (low + high) / 2;
        long long sum = 0;

        for(int i = 0; i < n; i++)
            if(tree[i] >= mid)
                sum += tree[i] - mid;
        
        if(sum >= m){
            ans = mid;
            low = mid + 1;
        }
        else{
            high = mid - 1;
        }
    } 

    cout << ans << '\n'; 

}
