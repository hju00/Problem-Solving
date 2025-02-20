#include <iostream>
#include <queue>

using namespace std;

int main() {
	
	int n, t;
	priority_queue<int> pq;
	cin >> n;

	while(n--)
	{
		scanf("%d", &t);
		if(t == 0){
			if(!pq.empty()){
				cout << -1 * pq.top() << "\n";
				pq.pop();
			}
			else	cout << "0\n"; 
		}
		else{
			pq.push(-1 * t);
		}

	}
	return 0;
}