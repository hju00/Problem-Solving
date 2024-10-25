#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> wallet, vector<int> bill) {
    int answer = 0;
    sort(wallet.begin(), wallet.end());
    sort(bill.begin(), bill.end());
    
    while(bill[0] > wallet[0] || bill[1] > wallet[1]){
        if (bill[0] > bill[1])
            bill[0] = bill[0] / 2;
        else
            bill[1] = bill[1] / 2;
        answer++;
        sort(wallet.begin(), wallet.end());
        sort(bill.begin(), bill.end());
    }
    return answer;
}