import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    
    static String S;
    static String T;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        
        int sLength = S.length();
        int tLength = T.length();
        
        Stack<Character> stack = new Stack<>();
        Stack<Character> tmp = new Stack<>();
        
        for(int i=0; i<sLength; i++){
            char ch = S.charAt(i);
            
            if(ch == T.charAt(tLength-1) && stack.size() >= tLength-1) {
                // 폭발 문자열 확인
                boolean isPossible = true;
                for (int j = tLength-2; j >=0 ; j--) {
                    char target = stack.pop();
                    tmp.add(target);
                    if(target != T.charAt(j)){
                        isPossible = false;
                        break;
                    }
                }
                
                if(!isPossible){
                    while(!tmp.isEmpty()){
                        stack.add(tmp.pop());
                    }
                    stack.add(ch);
                } else {
                    tmp.clear();
                }
            } else {
                // 아니면 넣기
                stack.add(ch);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        if(sb.length() != 0){
            System.out.println(sb.reverse());
        } else {
            System.out.println("FRULA");
        }
    }
}
