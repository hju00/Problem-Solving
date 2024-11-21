#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(){

    vector<int> v, result;
    int N, K;

    cin >> N >> K;

    for(int i = 1; i <= N; i++){
        v.push_back(i);
    }

    int index = 0;

    while (!v.empty()) {
        index = (index + K - 1) % v.size();  // 현재 벡터 크기 내에서 인덱스 갱신
        result.push_back(v[index]);         // 제거할 인덱스의 값 추가
        v.erase(v.begin() + index);         // 벡터에서 해당 값 제거
    }

    cout << "<" << result[0];
    for(int i = 1; i < N; i++)
        cout << ", " << result[i];
    cout << ">" << endl;
    
    return 0;
}