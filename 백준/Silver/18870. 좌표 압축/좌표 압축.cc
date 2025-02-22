#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

int main() {
    int N;
    cin >> N;

    vector<int> v(N);
    for (int i = 0; i < N; i++)		scanf("%d", &v[i]);

    // 원본 벡터 복사 후 정렬
    vector<int> sortedV = v;
    sort(sortedV.begin(), sortedV.end());

    // 중복 제거
    sortedV.erase(unique(sortedV.begin(), sortedV.end()), sortedV.end());

    // 좌표 압축: 값 -> 인덱스 매핑
    unordered_map<int, int> compressed;
    for (int i = 0; i < sortedV.size(); i++) {
        compressed[sortedV[i]] = i;
    }

    // unordered_map의 특징
	// ✅ 빠른 탐색 속도 → 평균적으로 O(1)에 가깝게 값 조회 가능
	// ✅ 정렬 필요 없음 → map과 달리 내부적으로 정렬되지 않음
	// ✅ 키-값 저장 방식 → compressed[원래 값] = 압축된 값 형태로 저장
    for (int x : v)
        cout << compressed[x] << " ";

    return 0;
}
