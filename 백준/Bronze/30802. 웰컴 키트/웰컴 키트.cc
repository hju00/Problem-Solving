#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(){

    long N, T, P;
    vector<long> t(6);

    cin >> N;
    cin >> t[0] >> t[1] >> t[2] >> t[3] >> t[4] >> t[5];
    cin >> T >> P;

    long result = 0;
    for(int i = 0; i < 6; i++){
        result += t[i] / T;
        if(t[i] % T > 0)    result++;
    }

    cout << result << endl;
    cout << N / P << " " << N % P << endl;

    return 0;
}