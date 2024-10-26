#include <iostream>
#include <queue>

using namespace std;

int main(){
    int N, t;
    string q_func;
    queue<int> q;
    cin >> N;
    for (int i = 0; i < N; i++){
        cin >> q_func;
        if(q_func == "push"){
            cin >> t;
            q.push(t);
        }
        if(q_func == "pop"){
            if(q.empty()) cout << -1 << endl;
            else {
                cout << q.front() << endl;
                q.pop();
            }
        }
        if(q_func == "size"){
            cout << q.size() << endl;
        }
        if(q_func == "empty"){
            if(q.empty()) cout << 1 << endl;
            else cout << 0 << endl;
        }
        if(q_func == "front"){
            if(q.empty()) cout << -1 << endl;
            else cout << q.front() << endl;
        }
        if(q_func == "back"){
            if(q.empty()) cout << -1 << endl;
            else cout << q.back() << endl;
        }
    }
}