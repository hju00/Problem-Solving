#include <string>
#include <vector>
#include <stdlib.h>

using namespace std;

int make_second(string s){
    int m10 = stoi(s.substr(0, 1)) * 10;
    int m1 = stoi(s.substr(1, 1));
    int s10 = stoi(s.substr(3, 1)) * 10;
    int s1 = stoi(s.substr(4, 1));
    return (m10 + m1) * 60 + s10 + s1;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    string answer = "";
    
    int video_sec = make_second(video_len);
    int pos_sec = make_second(pos);
    int op_start_sec = make_second(op_start);
    int op_end_sec = make_second(op_end);
    
    for(auto i : commands){
        if(pos_sec >= op_start_sec && pos_sec <= op_end_sec)    pos_sec = op_end_sec;
        
        if(i == "prev"){
            if(pos_sec < 10)    pos_sec = 0;
            else pos_sec -= 10;
        }
        else if(i == "next"){
            if(video_sec - pos_sec < 10)    pos_sec = video_sec;
            else pos_sec += 10;
        }
    }
    if(pos_sec >= op_start_sec && pos_sec <= op_end_sec)    pos_sec = op_end_sec;
    
    int mm = pos_sec / 60;
    int ss = pos_sec % 60;
    
    if(mm < 10) answer += "0";
    answer += to_string(mm) + ":";
    if(ss < 10) answer += "0";
    answer += to_string(ss);
    
    return answer;
}