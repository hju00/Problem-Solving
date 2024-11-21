#include<iostream>
#include<vector>
using namespace std;

int main(){

    int T, count_0, count_1;
    cin >> T;

    vector<pair<int, int>> v(41);
    v[0] = make_pair(1, 0);
    v[1] = make_pair(0, 1);

    for(int i = 2; i < 41; i++){
        count_0 = v[i - 1].first + v[i - 2].first;
        count_1 = v[i - 1].second + v[i - 2].second;
        v[i] = make_pair(count_0, count_1);
    }


    for(int test_case = 1; test_case <= T; test_case++)
    {
        int N;
        cin >> N;
        
        cout << v[N].first << ' ' << v[N].second << endl;

    }
    
    return 0;
}