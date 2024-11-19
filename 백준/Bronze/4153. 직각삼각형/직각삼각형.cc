#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main(){
    
    int a, b, c;
    a = 1, b = 1, c = 1;
    vector<int> v;
    while(true)
    {

        cin >> a >> b >> c;
        if(a == 0 || b == 0 || c == 0)
            break;

        v.push_back(a);
        v.push_back(b);
        v.push_back(c);

        sort(v.begin(), v.end());
        a = v[0];
        b = v[1];
        c = v[2];
        
        if(a * a + b * b == c * c)
            cout << "right" << endl;
        else
            cout << "wrong" << endl;

        v.clear();

    }

    return 0;
}