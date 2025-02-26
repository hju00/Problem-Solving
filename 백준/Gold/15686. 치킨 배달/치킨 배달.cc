#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;	// (2 ≤ N ≤ 50) (1 ≤ M ≤ 13)
vector<pair<int, int>> houses;
vector<pair<int, int>> chickens;
vector<vector<int>> chicken_combinations;

void make_combinations(vector<int> &comb, int start, int depth) {
    if (depth == M) { 
        chicken_combinations.push_back(comb);
        return;
    }
    for (int i = start; i < chickens.size(); i++) {
        comb.push_back(i);
        make_combinations(comb, i + 1, depth + 1);
        comb.pop_back();
    }
}

int get_city_chicken_distance(vector<int> &chicken_idxs) {
    int total_distance = 0;
    for (auto &[hx, hy] : houses) {
        int min_dist = 1e9; // 큰 값으로 초기화
        for (int idx : chicken_idxs) {
            int cx = chickens[idx].first;
            int cy = chickens[idx].second;
            int distance = abs(hx - cx) + abs(hy - cy);
            min_dist = min(min_dist, distance);
        }
        total_distance += min_dist;
    }
    return total_distance;
}


int main() {
	
	cin >> N >> M;

	int temp;
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			cin >> temp;
			if(temp == 0)	continue;
			else if(temp == 1)	houses.push_back({i, j});
			else if(temp == 2)	chickens.push_back({i, j});
		}
	}

	// 모든 조합 생성
    vector<int> comb;
    make_combinations(comb, 0, 0);

    // 모든 조합에 대해 최소 치킨 거리 계산
    int answer = 1e9;
    for (auto &combination : chicken_combinations) {
        answer = min(answer, get_city_chicken_distance(combination));
    }

    cout << answer << "\n";


	return 0;
}