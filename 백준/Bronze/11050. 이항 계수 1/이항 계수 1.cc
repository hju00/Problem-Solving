#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(){

    int N, K;
    cin >> N >> K;

    K = max(K, N - K);

    if(K == 0 || K == N){
        cout << 1 << endl;
        return 0;
    }

    int a = 1, b = 1;

    for(int i = 1; i <= K; i++){
        a *= N;
        b *= i;
        N--;
    }

    cout << a / b << endl;

    return 0;
}