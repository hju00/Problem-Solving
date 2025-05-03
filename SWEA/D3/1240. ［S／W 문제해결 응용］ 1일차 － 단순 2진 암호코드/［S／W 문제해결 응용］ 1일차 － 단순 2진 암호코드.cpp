#include<iostream>
#include<vector>
#include<string>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int T;
    cin >> T;

    string code_list[10] = {
        "0001101", "0011001", "0010011", "0111101", "0100011", 
        "0110001", "0101111", "0111011", "0110111", "0001011"
    };

    for (int test_case = 1; test_case <= T; ++test_case) {
        int N, M;
        cin >> N >> M;

        vector<string> cypertext(N);
        for (int i = 0; i < N; i++) {
            cin >> cypertext[i];
        }

        int row = -1, end = -1;

        // 1이 마지막에 있는 줄 찾기
        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 55; j--) {
                if (cypertext[i][j] == '1') {
                    row = i;
                    end = j;
                    break;
                }
            }
            if (row != -1) break;
        }

        int start = end - 55;
        string code = cypertext[row].substr(start, 56);

        string ans = "";
        for (int i = 0; i < 8; i++) {
            string part = code.substr(i * 7, 7);
            for (int d = 0; d < 10; d++) {
                if (part == code_list[d]) {
                    ans += ('0' + d);
                    break;
                }
            }
        }

        int checksum = 0, total = 0;
        for (int i = 0; i < 8; i++) {
            int num = ans[i] - '0';
            if (i % 2 == 0) checksum += num * 3;
            else checksum += num;
            total += num;
        }

        if (checksum % 10 == 0) cout << "#" << test_case << " " << total << "\n";
        else cout << "#" << test_case << " 0\n";
    }

    return 0;
}