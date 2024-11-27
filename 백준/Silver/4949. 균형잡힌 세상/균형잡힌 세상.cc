#include <iostream>
#include <string>
#include <stack>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    while(true) {
        string s;
        getline(cin, s);
        stack<char> t;
        bool is_balanced = true;

        if(s == ".")    break;

        for(auto temp : s){
            if(temp == '(' || temp == '['){
                t.push(temp);
            }
            else if(temp == ')'){
                if(!t.empty() && t.top() == '(')
                    t.pop();
                else{
                    is_balanced = false;
                    break;
                }
            }
            else if(temp == ']'){
                if(!t.empty() && t.top() == '[')
                    t.pop();
                else{
                    is_balanced = false;
                    break;
                }
            }
        }

        if(!t.empty())
            is_balanced = false;

        cout << (is_balanced ? "yes" : "no") << '\n';
    }

    return 0;
}
