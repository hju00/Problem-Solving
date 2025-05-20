#include<iostream>
#include<string>
#include<vector>
using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	
	cin >> T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        string s;
        cin >> s;

        vector<int> v;

        for(char c : s) {
            if(c >= 'A' && c <= 'Z')         v.push_back(c - 'A');
            else if(c >= 'a' && c <= 'z')    v.push_back(c - 'a' + 26);
            else if(c >= '0' && c <= '9')    v.push_back(c - '0' + 52);
            else if(c == '+')                v.push_back(62);
            else if(c == '/')                v.push_back(63);
        }

        string result = "";
        
        for(int i = 0; i < v.size(); i += 4) {
            int t = (v[i] << 18) | (v[i+1] << 12) | (v[i+2] << 6) | v[i+3];

            char first = (t >> 16) & 0xFF;
            char second = (t >> 8) & 0xFF;
            char third = t & 0xFF;

            result += first;
            result += second;
            result += third;
        }

        cout << "#" << test_case << " " << result << "\n";
	}
	return 0;
}