#include <bits/stdc++.h>
using namespace std;

int grid[501][501];
int n, m;
int maxSum = 0;

void square() {
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i + 1][j] + grid[i + 1][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	}
}

void keyblock() {
	for (int i = 0; i < n - 3; i++) {
		for (int j = 0; j < m; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i + 1][j] + grid[i + 2][j] + grid[i + 3][j];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // |
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m - 3; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i][j + 3];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // -
}


void tblock() {
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j + 1] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // 凸

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // T

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i + 1][j] + grid[i + 2][j] + grid[i + 1][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // ㅏ

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i + 1][j] + grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // ㅓ
}

void lblock() {
	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i + 1][j] + grid[i + 2][j] + grid[i + 2][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // L

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] + grid[i + 2][j];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // L대칭

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // ㄱ

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i + 1][j] + grid[i + 2][j];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // ㄱ 대칭

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j + 2];
			maxSum = sum > maxSum ? sum : maxSum;
		} 
	} // 가로로 긴 ㄱ

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // 가로로 긴 ㄱ 대칭

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // 가로로 긴 ㄴ

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j + 2] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	} // 가로로 긴 ㄴ 대칭
}

void zblock() {
	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 1][j + 2];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	}

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < m - 2; j++) {
			int sum = 0;
			sum = sum + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j] + grid[i + 1][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	}

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 2][j + 1];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	}

	for (int i = 0; i < n - 2; i++) {
		for (int j = 0; j < m - 1; j++) {
			int sum = 0;
			sum = sum + grid[i][j + 1] + grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 2][j];
			maxSum = sum > maxSum ? sum : maxSum;
		}
	}
}


int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> grid[i][j];
		}
	}

	square();
	zblock();
	tblock();
	lblock();
	keyblock();

	cout << maxSum << '\n';

	return 0;
}