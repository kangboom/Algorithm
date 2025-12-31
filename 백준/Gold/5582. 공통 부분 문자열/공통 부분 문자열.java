import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        int len1 = str1.length();
        int len2 = str2.length();
        
        int ans = 0;
        int[][] dp = new int[len2+1][len1+1];
        for(int i=1; i<=len2; i++){
            for(int j=1; j<=len1; j++){
                if(str1.charAt(j-1) == str2.charAt(i-1)){
                    dp[i][j] += dp[i-1][j-1] + 1;
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        
        System.out.println(ans);
    }
}
