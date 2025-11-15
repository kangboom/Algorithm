import java.util.*;

class Solution {
    String[][] tickets;
    boolean[] visited;
    boolean isFirst = false;
    String[] answer;
    Map<String, ArrayList<Info>> graph;
    public String[] solution(String[][] tmp) {
        tickets = tmp;
        visited = new boolean[tickets.length];
        answer = new String[tickets.length+1];
        
        graph = new HashMap<>();    
        for(int i=0; i<tickets.length; i++){
            String start = tickets[i][0];
            String dest = tickets[i][1];
            
            if(graph.get(start) == null){
                graph.put(start, new ArrayList<>());
            }
            Info newInfo = new Info(dest, i);
            graph.get(start).add(newInfo);
        }
        
        for(Map.Entry<String, ArrayList<Info>> entry : graph.entrySet()){
            ArrayList<Info> list = entry.getValue();
            Collections.sort(list, (o1, o2) -> {
                return (o1.dest).compareTo(o2.dest);
            });
        }
        
        String[] route = new String[tickets.length+1];
        route[0] = "ICN";
        dfs("ICN", 1, route);
        
        return answer;
    }
    
    void dfs(String start, int depth, String[] route){
        if(isFirst) return ;
        
        if(depth == route.length){
            isFirst = true;
            for(int i=0; i<route.length; i++){
                answer[i] = route[i];
            }
            return ;
        } 
        
        if(graph.get(start) == null) return; 
        for(int i=0; i<graph.get(start).size(); i++){
            Info info = graph.get(start).get(i);
            
            if(visited[info.idx]) continue;
            visited[info.idx] = true;
            route[depth] = info.dest;
            dfs(info.dest, depth+1, route);
            visited[info.idx] = false;
        }
    }
    
    class Info {
        String dest;
        int idx;
        
        Info(String dest, int idx){
            this.dest = dest;
            this.idx = idx;
        }
    }
}