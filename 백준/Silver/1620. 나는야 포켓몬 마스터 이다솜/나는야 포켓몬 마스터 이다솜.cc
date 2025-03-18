#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);
	
    int N, M;
    cin >> N >> M;

    vector<string> pokemons(N + 1);  
    unordered_map<string, int> name_to_index;  // 이름 → 번호 매핑

    for (int i = 1; i <= N; i++) {
        cin >> pokemons[i];
        name_to_index[pokemons[i]] = i;  // 이름을 키로, 번호를 값으로 저장
    }

    for (int t = 0; t < M; t++) {
        string query;
        cin >> query;

        if (isdigit(query[0])) {  // 숫자인 경우 (포켓몬 번호로 이름 찾기)
            int idx = stoi(query);
            cout << pokemons[idx] << '\n';
        } else {  // 문자열인 경우 (이름으로 번호 찾기)
            cout << name_to_index[query] << '\n';
        }
    }

    return 0;
}
