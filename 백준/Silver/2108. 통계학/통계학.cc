#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 
    // 단, N은 홀수이다. 그 다음 N개의 줄에는 정수들이 주어진다. 
    // 입력되는 정수의 절댓값은 4,000을 넘지 않는다.

    int n, temp;
    double sum = 0;
    cin >> n;
    int center_idx = n / 2;
    vector<int> num(n);
    vector<int> frequency(8001, 0);

    for(int i = 0; i < n; i++){
        cin >> temp;
        num[i] = temp;
        sum += (double) temp;
        frequency[temp + 4000]++;
    }

    sort(num.begin(), num.end());

    int max_fq = 0;
    vector<int> modes;
    for(int i = 0; i <= 8000; i++){
        if(frequency[i] > max_fq){
            max_fq = frequency[i];
            modes.clear();
            modes.push_back(i - 4000);
        }
        else if(frequency[i] == max_fq)
            modes.push_back(i - 4000);
    }

    
    cout << floor(sum / n + 0.5) << '\n';
    cout << num[center_idx] << '\n';
    if(modes.size() > 1)
        cout << modes[1] << '\n';
    else
        cout << modes[0] << '\n';
    cout << num[n - 1] - num[0] << '\n';
    return 0;
}
