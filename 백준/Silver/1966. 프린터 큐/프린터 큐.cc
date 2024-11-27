#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int T;
    cin >> T;
    
    for(int test_case = 1; test_case <= T; test_case++)
    {
        int n, m, temp;
        int ans = 0;
        cin >> n >> m;

        queue<pair<int, int>> q;
        priority_queue<int> pq;

        for(int i = 0; i < n; i++){
            cin >> temp;
            q.push(make_pair(temp, i));
            pq.push(temp);
        }

        while(!q.empty())
        {
            int current_priority = q.front().first;
            int current_index = q.front().second;
            q.pop();

            if(current_priority == pq.top()){
                pq.pop();
                ans++;
                if(current_index == m){
                    cout << ans << '\n';
                    break;
                }
            }
            else
                q.push(make_pair(current_priority, current_index));
        }
    }
    return 0;
}
