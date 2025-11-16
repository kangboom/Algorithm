import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        // target이 words에 포함되는 지 검사
        boolean isPossible = false;
        for(int i=0; i<words.length; i++){
            if(words[i].equals(target)){
                isPossible = true;
                break;
            }
        }
        
        if(!isPossible){
            return answer;
        }
        
        Map<String, ArrayList<String>> graph = new HashMap<>();
        graph.put(begin, new ArrayList<>());
        for(int i=0; i<words.length; i++){
                String str = words[i];
                
                int diff = 0;
                for(int k=0; k<str.length(); k++){
                    if(begin.charAt(k) != str.charAt(k)){
                        diff++;
                    }
                }
                
                if(diff == 1){
                    if(!graph.containsKey(str)){
                        graph.put(str, new ArrayList<>());
                    }
                    
                    graph.get(begin).add(str);
                    graph.get(str).add(begin);
                }
        }
        
        // 출발지로부터 갈 수 있는 게 없을 경우
        if(graph.get(begin).size() == 0){
            return answer;
        }
        
        for(int i=0; i<words.length-1; i++){
            for(int j=i+1; j<words.length; j++){
                String str1 = words[i];
                String str2 = words[j];
                
                int diff = 0;
                for(int k=0; k<str1.length(); k++){
                    if(str1.charAt(k) != str2.charAt(k)){
                        diff++;
                    }
                }
                
                if(diff == 1){
                    if(!graph.containsKey(str1)){
                        graph.put(str1, new ArrayList<>());
                    }
                    
                    if(!graph.containsKey(str2)){
                        graph.put(str2, new ArrayList<>());
                    }
                    
                    graph.get(str1).add(str2);
                    graph.get(str2).add(str1);
                }
            }
        }
        
        // target이 words에 있는 경우 탐색하기
        Map<String, Boolean> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(begin, 0));
        visited.put(begin, true);
        
        while(!q.isEmpty()){
            Node now = q.poll();
            for(String next : graph.get(now.word)){
                if(visited.containsKey(next)) continue;
                visited.put(next, true);
                q.offer(new Node(next, now.depth+1));
                
                if(next.equals(target)){
                    answer = now.depth + 1;
                    return answer;
                }
            }
        }
        
        return answer;
    }
    
    static class Node {
        String word;
        int depth;
        
        public Node(String word, int depth){
            this.word = word;
            this.depth = depth;
        }
    }
}