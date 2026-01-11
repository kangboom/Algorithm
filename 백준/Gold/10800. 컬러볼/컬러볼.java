import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] ans = new int[N+1];
        int[] colors = new int[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Queue<Node> q = new ArrayDeque<>();
        StringTokenizer st;
        int total = 0;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            
            total += size;
            pq.offer(new Node(i, size, color));
            colors[color] += size;
        }
        
        while(!pq.isEmpty()){
            // 현재 가장 큰 사이즈 확인
            int peek = pq.peek().size;
            
            // 같은 사이즈가 있는 경우 다 꺼내기
            while(!pq.isEmpty() && pq.peek().size == peek){
                Node now = pq.poll();
                q.offer(now);
                total -= now.size;
                colors[now.color] -= now.size;
            }
            
            while(!q.isEmpty()) {
                Node now = q.poll();
                ans[now.number] = total - colors[now.color];
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb);
    }
    
    static class Node implements Comparable<Node> {
        int number;
        int size;
        int color;
        
        Node(int number, int size, int color){
            this.number = number;
            this.size = size;
            this.color = color;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.size == o.size){
                if(this.number == o.number) return Integer.compare(this.color, o.color);
                else return Integer.compare(this.number, o.number);
            } else {
                return Integer.compare(this.size, o.size) * -1;
            }
        }
    }
}
