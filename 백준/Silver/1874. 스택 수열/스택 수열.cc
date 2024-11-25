#include<iostream>
#include<vector>
#include<stack>
#include<queue>
using namespace std;

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int n ,temp;  // n (1 ≤ n ≤ 100,000)
    cin >> n;
    stack<int> st;
    queue<int> q;
    vector<char> ans;

    for(int i = 0; i < n; i++){
        cin >> temp;
        q.push(temp);
    }

    int up = 1;
    while(!q.empty()){
        if(st.empty()){
            st.push(up++);
            ans.push_back('+');
        }
        else if(st.top() == q.front()){
            q.pop();
            st.pop();
            ans.push_back('-');
        }
        else if(st.top() < q.front()){
            st.push(up++);
            ans.push_back('+');
        }
        else{
            cout << "NO" << '\n';
            return 0;
        }
    }

    for(auto i : ans)
        cout << i << '\n';


    return 0;
}
