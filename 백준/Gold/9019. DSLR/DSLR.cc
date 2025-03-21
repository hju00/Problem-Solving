#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int A, B;
vector<bool> visited;

void bfs()
{
	queue<pair<int, string>> q;
	visited[A] = true;
	q.push({A, ""});

	while(!q.empty())
	{
		int cur_int = q.front().first;
		string cur_path = q.front().second;
		q.pop();

		if(cur_int == B){
			cout << cur_path << '\n';
			return;
		}
		
		int D = (cur_int * 2) % 10000;
		int S = (cur_int == 0) ? 9999 : cur_int - 1;
		int L = (cur_int % 1000) * 10 + (cur_int / 1000);
		int R = (cur_int / 10) + (cur_int % 10) * 1000;

		// 방문하지 않은 숫자만 큐에 삽입 (최단 경로 보장)
        if (!visited[D]) { 
			visited[D] = true;
			q.push({D, cur_path + "D"});
		}
        if (!visited[S]) { 
			visited[S] = true;
			q.push({S, cur_path + "S"});
		}
        if (!visited[L]) { 
			visited[L] = true;
			q.push({L, cur_path + "L"});
		}
        if (!visited[R]) { 
			visited[R] = true;
			q.push({R, cur_path + "R"});
		}
	}

}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	int T;
	cin >> T;

	for(int test_case = 1; test_case <= T; test_case++)
	{
		cin >> A >> B;
		visited.assign(10000, false);
		bfs();
	}

	return 0;
}