import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N+1];
        int[] dp = new int[N+1];
        
        for(int i=1; i<=N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        nums[0] = 10_000_001;
        for(int i=1; i<=N; i++){
            // 뒤에 탐색
            int tmp = 0;
            for(int j=i-1; j>=0; j--){
                if(nums[i] < nums[j]){
                    tmp = Math.max(tmp, dp[j]);
                }
            }
            dp[i] = tmp +1;
        }
        
        int maxLen = 0;
        for(int i=1; i<=N; i++){
            maxLen = Math.max(maxLen, dp[i]);
        }
        System.out.println(N-maxLen);
    }
}
