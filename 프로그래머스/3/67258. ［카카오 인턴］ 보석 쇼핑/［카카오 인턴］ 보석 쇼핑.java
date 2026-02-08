import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> gemsSet = new HashSet<>();
        for(int i=0; i<gems.length; i++){
            gemsSet.add(gems[i]);
        }
        
        int totalCnt = gemsSet.size();
        
        HashMap<String, Integer> cnt = new HashMap<>();
        int left = 0;
        int right = 0;
        int kind = 0;
        int minLen = 100_001;
        while(right < gems.length){
            String curGem = gems[right];
            
            int curCnt = cnt.getOrDefault(curGem, 0);
            if(curCnt == 0){
                kind++;
            }
            cnt.put(curGem, curCnt+1);
            
            // 왼쪽 제거
            while(kind == totalCnt){
                
                // 정답 갱신
                int curLen = right-left+1;
                if(minLen > curLen){
                    minLen = curLen;
                    answer[0] = left+1;
                    answer[1] = right+1;
                }
                
                String leftGem = gems[left];
                int leftGemCnt = cnt.get(leftGem);
                cnt.put(leftGem, --leftGemCnt);
                
                if(leftGemCnt == 0){
                    kind--;
                }
                left++;
            }
            
            right++;
        }
        return answer;
    }
}

/**
    1. 모두 탐색하여 몇 가지 종류의 보석이 있는 지 기억하기
    2. 투 포인터로 탐색
        - 오른쪽으로 한 칸 이동
        - 만약 없는 보석이다. -> kind++
        - 만약 있는 보석이다. -> 왼쪽 한 칸 이동
    3. 끝까지 돌려보기 -> 최소가 있을 수도 있으므로
        - 종료 조건 : right가 N일때
    4. 오른쪽이동 조건: kind 개수가 보석 종류보다 작을 떄,
        왼쪽 이동 조건 : kind 개수가 보석 종류랑 같을 때, kind가 떨어질 때 까지 
    
    필요한 자료구조
    - HashSet<String, Integer> cnt : 현재 포함된 보석의 개수
    - left, right : 투 포인터
    - ans : 최솟값 기억, right - left + 1 최대
    - ansLeft, ansRight : 최대 일때, 왼쪽 오른 쪽 값 / 인덱스 1부터 시작
**/