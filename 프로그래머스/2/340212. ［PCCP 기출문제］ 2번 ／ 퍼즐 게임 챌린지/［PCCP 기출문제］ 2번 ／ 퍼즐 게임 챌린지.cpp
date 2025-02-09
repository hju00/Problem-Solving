#include <string>
#include <vector>
#include <algorithm>

using namespace std;

// 1 ≤ diffs[i] ≤ 100,000
// 1 ≤ times[i] ≤ 10,000
// 1 ≤ limit ≤ 10^15

int solution(vector<int> diffs, vector<int> times, long long limit) {
    int answer = 0;
    
    int low = 1;
    int high = *max_element(diffs.begin(), diffs.end());
    int mid = (low + high) / 2;
    long long sum_time = 0;
    
    while(low <= high){
        
        mid = (low + high) / 2;
        sum_time = 0;
        
        for(int i = 0; i < diffs.size(); i++){
            if(mid < diffs[i]) 
                sum_time += (times[i - 1] + times[i]) * (diffs[i] - mid);
            sum_time += times[i];
        }
        
        if(sum_time <= limit)    high = mid - 1;
        else    low = mid + 1;
    }
    
    answer = low;
    return answer;
}