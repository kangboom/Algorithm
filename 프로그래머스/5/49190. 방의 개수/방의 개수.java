import java.util.*;
 
class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        
        Map<Node, ArrayList<Node>> map = new HashMap<>();
        Node now = new Node(0, 0);
        
        for(int arrow : arrows){
            for(int i=0; i<=1; i++){
                Node next = new Node(now.x + dx[arrow], now.y + dy[arrow]);
                if(!map.containsKey(next)){
                    map.put(next, makeList(now));
                    
                    if(!map.containsKey(now)){
                        map.put(now, makeList(next));
                    } else {
                        map.get(now).add(next);
                    }
                    
                } else if(!map.get(next).contains(now)){
                    map.get(next).add(now);
                    map.get(now).add(next);
                    answer++;
                }
                now = next;
            }  
        }
        
        return answer;
    }
    
    ArrayList<Node> makeList(Node node){
        ArrayList<Node> newNode = new ArrayList<>();
        newNode.add(node);
        return newNode; 
    }
    
    static class Node {
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
        
        public int hashCode(){
            return Objects.hash(x, y);
        }
    }
}