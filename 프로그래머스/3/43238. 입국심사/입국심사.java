import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long left = 1;
        long right = times[times.length-1] * (long)n;
                
        while(left<=right){
            long mid = (left + right) / 2;
            if(check(mid, times, n)){
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
    
    static boolean check(long time, int[] times, int n) {
        
        long cnt = 0;
        for(int t : times){
            cnt += (time / t);
        }
        
        if(cnt >= n){
            return true;
        }
        return false;
    }
}