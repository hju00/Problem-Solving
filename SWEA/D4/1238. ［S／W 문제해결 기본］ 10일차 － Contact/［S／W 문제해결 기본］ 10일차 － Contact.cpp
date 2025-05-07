#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

int bfs(int s, vector<int> v[101])
{
    int max_p = s;
    queue<int> q;
    bool visited[101] = { false };
    visited[s] = true;
    q.push(s);

    while(!q.empty())
    {
        int cycle = q.size();
        if(cycle > 0)   max_p = 0;

        for(int i = 0; i < cycle; i++)  {

            int cur = q.front();
            q.pop();
            max_p = max(max_p, cur);
            
            for(int nx : v[cur])    {
                if(!visited[nx])    {
                    visited[nx] = true;
                    q.push(nx);
                }
            }
        }

    }

    return max_p;
}

int main(int argc, char** argv)
{
    ios::sync_with_stdio(false);
    cin.tie(0);

	int test_case;
	int T = 10;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int t, start;
        cin >> t >> start;

        vector<int> map[101];
        t /= 2;

        while(t--)  {
            int from, to;
            cin >> from >> to;
            map[from].push_back(to);
        }

        cout << "#" << test_case << " " << bfs(start, map) << "\n";
	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}