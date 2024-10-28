#include <iostream>
#include <string>
#define MAX 99999
using namespace std;

int main() {

	int temp;
	cin >> temp;
	string s;

	while (temp != 0) {
		
		bool check = true;
		s = to_string(temp);
		for (int i = 0; i < s.length() / 2; i++) {
			if (s[i] != s[s.length() - 1 - i])
				check = false;
		}
		if (check)
			cout << "yes" << endl;
		else
			cout << "no" << endl;

		cin >> temp;
	}

	return 0;
}