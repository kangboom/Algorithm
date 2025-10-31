import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[N];
        for(int i=0; i<N; i++){
            while(!stack.empty() && arr[stack.peek()] < arr[i]){
                ans[stack.pop()] = arr[i];
            }
            stack.add(i);
        }
        
        while(!stack.isEmpty()){
            ans[stack.pop()] = -1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}
