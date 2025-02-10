#include <string>
#include <vector>
#include <map>

using namespace std;

map<pair<int, pair<int, int>>, int> logs;

void update(pair<int, pair<int, int>> key)
{
    if (logs.find(key) != logs.end())
        logs[key]++;
    else
        logs.insert({key, 1});
}

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    
    for (auto& route : routes)
    {
        int time = 0;
        for (int i = 0; i < route.size() - 1; i++)
        {
            int start = route[i] - 1;
            int dest = route[i + 1] - 1;
   
            int y = points[start][0];
            int x = points[start][1];
            
            int dirY = points[dest][0] - y > 0 ? 1 : -1;
            int dirX = points[dest][1] - x > 0 ? 1 : -1;
            
            if (i == 0)
                update({time++, {y, x}});
            
            while (y != points[dest][0])
            {
                y += dirY;
                update({time++, {y, x}});
            }
            
            while (x != points[dest][1])
            {
                x += dirX;
                update({time++, {y, x}});
            }
        }
    }

    int answer = 0;
    for (auto& log : logs)
    {
        int cnt = log.second;
        if (cnt > 1)
            answer++;     
    }
    
    return answer;
}