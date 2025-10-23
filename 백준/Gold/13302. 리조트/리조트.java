import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static boolean[] visited;
    static int[][] dp;
    static int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws  Exception {
        input();
        solve(0, 0, 0);
        
        int ans = INF;
        for(int i=0; i<=40; i++) {
        	ans = Math.min(ans, dp[N][i]);
        }
        System.out.println(ans);
    }

    static void solve(int depth, int total, int coupon){

    	dp[0][0] = 0;
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<40; j++) {
  
    			int tmp = dp[i][j];
    			
    			if(tmp == INF) {
    				continue;
    			}
    			
    			if(visited[i+1]) {
    				dp[i+1][j] = Math.min(dp[i+1][j], tmp);
    			}
    			
    			if(j>=3) {
    				dp[i+1][j-3] = Math.min(dp[i+1][j-3], tmp);
    			}
    			
    			// 1일권
    			dp[i+1][j] = Math.min(dp[i+1][j], tmp + 10000);
    			
    			// 3일권
    			for(int k=1; k<=3; k++) {
    				dp[i+k][j+1] = Math.min(dp[i+k][j+1], tmp + 25000);
    			}
    			
    			// 5일권
    			for(int k=1; k<=5; k++) {
    				dp[i+k][j+2] = Math.min(dp[i+k][j+2], tmp + 37000);
    			}
    		}
    	}
    }
    
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        dp = new int[N+5][42]; // 쿠폰 최대 40장
        for(int i=0; i<=N; i++) {
        	for(int j=0; j<=40; j++){
        		dp[i][j] = INF;
        	}
        }
        if(M == 0) return ;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int day = Integer.parseInt(st.nextToken());
            visited[day] = true;
        }
    }
}
