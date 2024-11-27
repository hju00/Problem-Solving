#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // K는 1이상 10,000이하의 정수
    // N은 1이상 1,000,000이하의 정수
    int k, n;
    long long sum = 0;
    cin >> k >> n;

    vector<long long> v(k);
    for(int i = 0; i < k; i++){
        cin >> v[i];
        sum += v[i];
    }
    
    sort(v.begin(), v.end());

    long long low = 1, high = v[k - 1];
    long long result = 0;

    // 이분 탐색
    while(low <= high)
    {
        long long mid = (low + high) / 2;
        long long count = 0;

        for(int i = 0; i < k; i++)
            count += v[i] / mid;
        
        if(count >= n){
            result = mid;   // 조건 만족 시 더 큰 길이 시도
            low = mid + 1;
        }
        else{
            high = mid - 1; // 조건 만족 못할 시 더 짧은 길이 시도
        }
    }

    cout << result << '\n';

    return 0;
}
