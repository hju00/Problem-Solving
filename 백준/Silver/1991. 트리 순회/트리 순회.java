import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    
    static int tree[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // A : 0 으로 해싱, [][0] : 왼쪽    [][1] : 오른쪽
        tree = new int[26][2];

        while(N-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 각 알파벳에서 A 의 아스키 값인 65를 빼주어 해싱해준다.  A : 0, B : 1, ...
            int parent = (int) st.nextToken().charAt(0) - 65;
            int left = (int) st.nextToken().charAt(0) - 65;
            int right = (int) st.nextToken().charAt(0) - 65;

            tree[parent][0] = left;
            tree[parent][1] = right;
        }

        preorder(0);
        System.out.println();

        inorder(0);
        System.out.println();

        postorder(0);
        System.out.println();
    }

    static void preorder(int c) {
        if(c == -19)    return;                 // '.' - 'A' = -19
        System.out.print((char) (c + 65));      // 1. 루트
        preorder(tree[c][0]);                   // 2. 왼쪽
        preorder(tree[c][1]);                   // 3. 오른쪽
    }

    static void inorder(int c)  {
        if(c == -19)    return;
        inorder(tree[c][0]);                    // 1. 왼쪽
        System.out.print((char) (c + 65));      // 2. 루트
        inorder(tree[c][1]);                    // 3. 오른쪽
    }

    static void postorder(int c)    {
        if(c == -19)    return;
        postorder(tree[c][0]);                  // 1. 왼쪽
        postorder(tree[c][1]);                  // 2. 오른쪽
        System.out.print((char) (c + 65));      // 3. 루트
    }
}