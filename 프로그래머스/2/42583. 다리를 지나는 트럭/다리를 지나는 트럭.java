import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0, sum = 0, i = 0;
        int truck_count = truck_weights.length;
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int j = 0; j < bridge_length; j++)
            q.add(0);
        
        while (!q.isEmpty()) {
            
            // 1초 흐름
            answer++;
            
            // 한 칸씩 이동
            sum -= q.poll();
            
            // 트럭이 모두 이동한 경우
            if(i == truck_count)    continue;
            
            // 다음 트럭이 다리에 올라갈 수 있는 경우
            if(sum + truck_weights[i] <= weight) {
                sum += truck_weights[i];
                q.add(truck_weights[i]);
                i++;
            } 
            // 다음 트럭이 다리에 올라갈 수 없는 경우
            else 
                q.add(0);
        }
        
        return answer;
    }
}