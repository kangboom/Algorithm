import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strs = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            strs[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strs, (o1, o2) -> {
            return -1*(o1+o2).compareTo(o2+o1);   
        });
        
        for(String str : strs){
            answer += str;
        }
        
        if(strs[0].equals("0")){
            answer = "0";
        }
            
        return answer;
    }
}