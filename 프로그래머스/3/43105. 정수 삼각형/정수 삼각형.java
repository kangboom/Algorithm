class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][];
        for(int i=0; i<triangle.length; i++){
            dp[i] = new int[i+1];
        }
        
        dp[0][0] = triangle[0][0];
        for(int i=0; i<triangle.length-1; i++){
            for(int j=0; j<=i; j++){
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
            }
        }
        for(int i=0; i<dp[triangle.length-1].length; i++){
            answer = Math.max(dp[triangle.length-1][i], answer);
        }
        return answer;
    }
}