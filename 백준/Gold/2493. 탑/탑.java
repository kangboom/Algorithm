import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] ans = new int[N+1];
        int[] stack = new int[N+1];
        int size = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=N; i>0; i--){
            while(size != 0 && arr[stack[size]] <= arr[i]){
                int index = stack[size--];
                ans[index] = i;
            }
            stack[++size] = i;
        }
        
        while(size != 0){
            int index = stack[size--];
            ans[index] = 0;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(ans[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}
