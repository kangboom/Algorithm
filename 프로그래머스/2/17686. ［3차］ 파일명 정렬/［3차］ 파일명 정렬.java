import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Filename[] filenames = new Filename[files.length];
        for(int i=0; i<files.length; i++){
            filenames[i] = new Filename(files[i]);
        }
        
        Arrays.sort(filenames);
        String[] ans = new String[filenames.length];
        for(int i=0; i<filenames.length; i++){
            ans[i] = filenames[i].original;
        }
        return ans;
    }
    
    static class Filename implements Comparable<Filename>{

        String head;
        int number;
        String original;
        
        public Filename(String original){
            this.head = getHead(original);
            this.number = getNumber(head.length(), original);
            this.original = original;
        }
        
        @Override
        public int compareTo(Filename o){
            if(this.head.equals(o.head)){
                return Integer.compare(this.number, o.number);
            }
            return this.head.compareTo(o.head);
        }
        
        private String getHead(String original){
            int startNumberIdx = 0;
            for(int i=0; i<original.length(); i++){
                char ch = original.charAt(i);
                if('0' <=  ch && ch <= '9') {
                    startNumberIdx = i;
                    break;
                }
            }
            return original.substring(0, startNumberIdx).toLowerCase();
        }
        
        private int getNumber(int startIdx, String original){
            int endIdx = 0;
            for(int i=startIdx; i<original.length(); i++){
                char ch = original.charAt(i);                
                if(!('0' <=  ch && ch <= '9')) {
                    break;
                }
                endIdx = i;
            }
            
            return Integer.parseInt(original.substring(startIdx, endIdx+1));
        }
    }
}