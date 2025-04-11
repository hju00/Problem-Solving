#include <iostream>
#include <vector>
using namespace std;

int N;	// 1 ≤ N ≤ 26
int tree[26][2];

void preorder(int cur) {
	if (cur == -19) return;			// '.' - 'A' = -19 
	cout << (char)(cur + 'A');      // 1. 루트
	preorder(tree[cur][0]);         // 2. 왼쪽
	preorder(tree[cur][1]);         // 3. 오른쪽
}

void inorder(int cur) {
	if (cur == -19) return;
	inorder(tree[cur][0]);          // 1. 왼쪽
	cout << (char)(cur + 'A');      // 2. 루트
	inorder(tree[cur][1]);          // 3. 오른쪽
}

void postorder(int cur) {
	if (cur == -19) return;
	postorder(tree[cur][0]);        // 1. 왼쪽
	postorder(tree[cur][1]);        // 2. 오른쪽
	cout << (char)(cur + 'A');      // 3. 루트
}

int main() {
	
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for(int i = 0; i < N; i++)	{
		char parent, left, right;
		cin >> parent >> left >> right;

		tree[parent - 'A'][0] = left - 'A';
		tree[parent - 'A'][1] = right - 'A';
	}

	preorder(0);
	cout << '\n';

	inorder(0);
	cout << '\n';

	postorder(0);
	cout << '\n';

	return 0;
}