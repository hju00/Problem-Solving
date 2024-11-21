#include<iostream>
#include<vector>
#include<cstdio>
using namespace std;

int main(){

    int N, M, temp;
    int card_plus[10000001] = { 0, };
    int card_minus[10000001] = { 0, };

    cin >> N;

    for(int i = 0; i < N; i++){
        scanf("%d", &temp);
        if(temp >= 0)    card_plus[temp]++;
        else{
            temp *= -1;
            card_minus[temp]++;
        }
    }

    cin >> M;

    for(int i = 0; i < M; i++){
        scanf("%d", &temp);
        if(temp >= 0)   printf("%d ", card_plus[temp]);
        else{
            temp *= -1;
            printf("%d ", card_minus[temp]);
        }
    }
    
    return 0;
}