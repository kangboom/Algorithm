class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        int[] queue = new int[2*n];
        long sum1 = 0;
        for(int i=0; i<n; i++){
            queue[i] = queue1[i];
            sum1 += queue[i];
        } 
        
        long sum2 = 0;
        for(int i=n; i<2*n; i++){
            queue[i] = queue2[i-n];
            sum2 += queue[i]; 
        }
        
        long totalSum = sum1 + sum2;
        
        // 홀수면 종료
        if(totalSum%2 == 1){
            return -1;
        }
        
        long target = (sum1 + sum2) / 2;
        
        int left = 0;
        int right = n-1;
        
        int cnt = 0;
        while(cnt < 4*n){
            if(sum1 == target){
                break;
            } else if(sum1 < target){
                right = (right+1)%(2*n);
                if(queue[right] > target) {
                    cnt = -1;
                    break;
                }
                cnt++;
                sum1 += queue[right];
            } else {
                if(queue[left] > target) {
                    cnt = -1;
                    break;
                }
                cnt++;
                sum1 -= queue[left];
                left = (left+1)%(2*n);
            }
        }
        
        if(cnt >= 4*n){
            return -1;
        } 
        return cnt;
    }
}