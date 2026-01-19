import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[N+1];
        int[] sum = new int[N+1];
        for(int i=1; i<=N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + nums[i];
        }
        
        int M = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][N+1];
        for(int i=1; i<=3; i++){
            for(int j=M; j<=N; j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-M] + sum[j]-sum[j-M]);
            }
        }
        
        System.out.println(dp[3][N]);
    }
}
