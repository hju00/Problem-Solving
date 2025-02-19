#include <iostream>
#include <unordered_set>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    unordered_set<string> listen;
    vector<string> result;
    string name;
    int n, m;

    cin >> n >> m;
    cin.ignore();

    while (n--) {
        getline(cin, name);
        listen.insert(name);
    }

    while (m--) {
        getline(cin, name);
        if (listen.find(name) != listen.end()) 
            result.push_back(name);
    }

    sort(result.begin(), result.end());

    cout << result.size() << '\n';
    for (const string& s : result) 
        cout << s << '\n';

    return 0;
}
