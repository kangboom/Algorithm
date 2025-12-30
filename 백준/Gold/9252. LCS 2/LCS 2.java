import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static String str1;
    static String str2;
    static int len1;
    static int len2;
    
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        
        len1 = str1.length();
        len2 = str2.length();
        
        int[][] dp = new int[len2+1][len1+1];
        for(int i=1; i<=len2; i++){
            for(int j=1; j<=len1; j++){
                if(str1.charAt(j-1) == str2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        int ansCnt = dp[len2][len1];
        sb = new StringBuilder();
        search(len2, len1, dp);
        
        System.out.println(ansCnt);
        if(ansCnt != 0){
            System.out.println(sb.reverse());
        }
    }
    
    static void search(int y, int x, int[][] dp){
        if(y == 0 || x == 0){
            return ;
        }
        
        if(str2.charAt(y-1)==str1.charAt(x-1)){
            sb.append(str1.charAt(x-1));
            search(y-1, x-1, dp);
        } else {
            if(dp[y-1][x] > dp[y][x-1]){
                search(y-1, x, dp);
            } else {
                search(y, x-1, dp);
            }
        }
    }
}
