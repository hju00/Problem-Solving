import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) 
        { 
            char chars[] = br.readLine().toCharArray();
            if(chars[0] == '.' & chars.length == 1)   break;
            
            Stack<Character> stackChar = new Stack<>();

            boolean res = true;

            for(char c : chars) {
                if (c == '(')   stackChar.push(c);
                else if (c == '[')  stackChar.push(c);
                else if (c == ')')  {
                    if(stackChar.empty() || stackChar.pop() != '(')  {
                        res = false;
                        break;
                    }
                }
                else if (c == ']')  {
                    if  (stackChar.empty() || stackChar.pop() != '[')    {
                        res = false;
                        break;
                    }
                }
            }
            if(!stackChar.empty())  res = false;

            if(res) System.out.println("yes");
            else    System.out.println("no");

        }
    }
}