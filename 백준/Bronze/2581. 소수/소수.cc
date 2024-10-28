#include <iostream>
#include <algorithm>

using namespace std;

// M과 N은 10,000이하의 자연수이며, M은 N보다 작거나 같다.
int M, N;
int sum = 0;   // 소수들의 합
int m = -1;  // 소수 중 최솟값

bool is_prime(int t){
    if(t < 2) return false;
    for(int i = 2; i * i <= t; i++){
        if (t % i == 0) return false;
    }
    return true;
}

void find_prime(){
    m = N;
    for(int i = M; i <= N; i++){
        if(is_prime(i)) {
            sum += i;
            if(m == -1 || m > i) m = i;
        }
    }
}

int main(){
    cin >> M >> N;

    find_prime();

    if(sum == 0){
        cout << -1;
    }
    else{
    cout << sum << endl;
    cout << m << endl;  
    }

    return 0;
}