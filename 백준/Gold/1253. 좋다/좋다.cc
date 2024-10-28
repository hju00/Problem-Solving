#include <iostream>
#include <algorithm>

using namespace std;
// '두' 수의 합으로 표현되는 좋은 수
// 두 개의 수 이므로 투포인터 알고리즘을 유추할 수 있다

int N;
long long A[2001];
int answer = 0;

void input(){
    cin >> N;
    for(int i = 0; i < N; i++)
        cin >> A[i];
    sort(A, A + N);
}

void twoPointer(){
    int start, end;
    for (int i = 0; i < N; i++){
        start = 0;
        end = N - 1;
        while(start < end){
            // 자기 자신 처리일 경우 예외처리 해주어야함
            if(start == i) {
                start++;
                continue;
            }
            if(end == i) {
                end--;
                continue;
            }
            if(A[start] + A[end] == A[i]){
                answer++;
                break;
            }
            else if(A[start] + A[end] < A[i])   start++;
            else end--;
        }
    }
}

int main(){
    input();
    twoPointer();
    cout << answer;
}