#include <iostream>
#include <queue>
#include <vector>
#include <string>
using namespace std;

// ✅ 해결 아이디어 (투 PQ + lazy deletion)
// 	1.	최소 힙과 최대 힙을 두 개 모두 사용.
// 	2.	각 힙에서 삭제 시, 동기화 문제 발생: 한 힙에서 삭제된 값이 다른 힙에 남아있을 수 있음.
// 	3.	이를 해결하기 위해 삽입된 원소마다 고유 id를 붙이고,
// 	4.	삭제된 id를 기록해서, 힙에서 꺼낼 때 이미 삭제된 애는 무시하는 방식으로 처리.
// 이걸 lazy deletion이라고 부름!

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int T;
    cin >> T;
    while (T--) {
        int K;
        cin >> K;

        priority_queue<pair<int, int>> maxHeap;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> minHeap;
        vector<bool> isValid(K, false);

        for (int i = 0; i < K; ++i) {
            char op;
            int num;
            cin >> op >> num;

            if (op == 'I') {
                maxHeap.push({num, i});
                minHeap.push({num, i});
                isValid[i] = true;
            }
            else if (op == 'D') {
                if (num == 1) {
                    while (!maxHeap.empty() && !isValid[maxHeap.top().second])
                        maxHeap.pop();
                    if (!maxHeap.empty()) {
                        isValid[maxHeap.top().second] = false;
                        maxHeap.pop();
                    }
                }
                else {
                    while (!minHeap.empty() && !isValid[minHeap.top().second])
                        minHeap.pop();
                    if (!minHeap.empty()) {
                        isValid[minHeap.top().second] = false;
                        minHeap.pop();
                    }
                }
            }
        }

        // cleanup
        while (!maxHeap.empty() && !isValid[maxHeap.top().second]) maxHeap.pop();
        while (!minHeap.empty() && !isValid[minHeap.top().second]) minHeap.pop();

        if (maxHeap.empty()) cout << "EMPTY\n";
        else cout << maxHeap.top().first << ' ' << minHeap.top().first << '\n';
    }

    return 0;
}