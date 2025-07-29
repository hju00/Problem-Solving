import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    static class Tower {
        public int index;
        public int height;

        Tower(int index, int height)    {
            this.index = index;
            this.height = height;
        }
    }
    static int N;
    static int towers[];
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        towers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++)  towers[i] = Integer.parseInt(st.nextToken());
        Stack<Tower> towerStack = new Stack<>();

        for(int i = 0; i < N; i++)  {
            Tower t = new Tower(i + 1, towers[i]);


            if(towerStack.empty())  System.out.print("0 ");         // 스택이 비어있을 경우 현재 탑 보다 높은 탑이 없는 경우 이므로 0 출력
            else    {
                while(!towerStack.empty())  {                         // 스택에 탑이 존재할 때 까지
                    Tower nt = towerStack.peek();                     // 스택 맨 위
                    if(nt.height >= t.height)   {                     // 스택 맨위의 탑의 높이가 현재 탑의 높이보다 클 경우
                        System.out.print(nt.index + " ");             // 스택 맨위 탑의 index 출력 후 반복문 탈출
                        break;
                    }
                    else    towerStack.pop();                         // 스택 맨위의 탑의 높이가 현재 탑의 높이보다 작은 경우 필요가 없으므로 pop
                }
                if(towerStack.empty())  System.out.print("0 ");     // 반복문 종료 후 스택이 비어있으면 현재 탑의 높이보다 큰 탑이 없었던 것이므로 0 출력
            }
            
            towerStack.push(t);                                       // 공통적으로 현재 탑 스택에 추가
        }
        
        
    }
}