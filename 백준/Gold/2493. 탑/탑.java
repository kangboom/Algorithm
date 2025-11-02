import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] ans = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int i=N; i>0; i--){
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]){
                int index = stack.pop();
                ans[index] = i;
            }
            stack.add(i);
        }
        
        while(!stack.isEmpty()){
            int index = stack.pop();
            ans[index] = 0;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(ans[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}
