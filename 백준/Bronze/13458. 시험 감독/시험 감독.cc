#include <iostream>
#include <vector>

using namespace std;

int main(){
    // N개의 시험장
    // 각 시험장에 있는 응시자의 수: A
    // 총감독관이 감시할 수 있는 응시자의 수: B, 부감독관이 감시할 수 있는 응시자의 수: C
    long long N, A, B, C;
    vector<long long> test;

    cin >> N;

    long long answer = N;

    for(int i = 0; i < N; i++){
        cin >> A;
        test.push_back(A);
    }

    cin >> B >> C;

    for(int i = 0; i < N; i++){
        int temp = test.at(i) - B;
        if (temp > 0){
            if (temp % C == 0)
                answer += temp / C;
            else   
                answer += temp / C + 1;
        }
    }

    cout << answer << endl;
}