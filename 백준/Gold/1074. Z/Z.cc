#include <iostream>
#include <cmath>
using namespace std;

int main() {

	int n, r, c;
	cin >> n >> r >> c;

	// c++에서는 2^2 2의 제곱이 아니다. pow함수 사용
	int d = pow(2, (n-1));
	int start = 0;

	while(d > 1) 
	{
		if(r < d && c < d)
			start += d * d * 0;
		if(r < d && c >= d){
			start += d * d * 1;
			c -= d;
		}
		if(r >= d && c < d){
			start += d * d * 2;
			r -= d;
		}
		if(r >= d && c >= d){
			start += d * d * 3;
			r -= d;
			c -= d;
		}
		
		d /= 2;
	}

	if(r == 0 && c == 0)
		cout << start << endl;
	else if(r == 0 && c == 1)
		cout << start + 1 << endl;
	else if(r == 1 && c == 0)
		cout << start + 2 << endl;
	else
		cout << start + 3 << endl;

	return 0;
}