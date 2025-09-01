class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        // 최댓값으로 시작
        int left = 1;
        int right = 100000;
        while(left<=right){
            int mid = (left+right)/2;
            
            // 검증 통과
            if(valid(mid, diffs, times, limit)){
                // level 낮추기
                answer = mid;
                right = mid-1;
            } else {
                // level 올리기
                left = mid+1;
            }
        }
        
        return answer;
    }
    
    static boolean valid(int level, int[] diffs, int[] times, long limit){
        long time = times[0];
        
        // 순서대로 문제 풀기
        for(int i=1; i<diffs.length; i++){
            // 쉬운 문제인 경우(level >= diff)
            if(level >= diffs[i]){
                time += times[i];
            } else { // level < diff
                int cnt = diffs[i] - level;
                time += (cnt * (times[i] + times[i-1]) + times[i]);
            }
            if(time > limit) {
                return false;
            }
        }
        
        return true;
    }
}