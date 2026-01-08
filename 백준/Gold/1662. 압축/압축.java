import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<String> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ')'){
                int strLength = 0;
                while(!stack.isEmpty()){
                    String str = stack.pop();
                    if(str.length() > 1){
                        strLength += Integer.parseInt(str.substring(0, str.length()-1));
                    } else {
                        if(str.charAt(0) == '('){
                            break;
                        } else {
                            strLength++;
                        }
                    }
                }
                int repeat = Integer.parseInt(stack.pop());
                int total = repeat*strLength;
                stack.add(total+"L");
            } else {
                stack.add(String.valueOf(s.charAt(i)));
            }
        }
        int ans = 0;
        while(!stack.isEmpty()){
            String str = stack.pop();
            if(str.length()>1){
                ans += Integer.parseInt(str.substring(0, str.length()-1));
            } else {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
