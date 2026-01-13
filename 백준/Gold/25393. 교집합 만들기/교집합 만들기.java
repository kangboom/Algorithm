import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    static int [] maxR;
    static int [] minL;
    static Map<Node, Boolean> map = new HashMap<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        maxR = new int[1_000_001];
        minL = new int[1_000_001];
        Arrays.fill(minL, Integer.MAX_VALUE);
        
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            
            maxR[left] = Math.max(maxR[left], right);
            minL[right] = Math.min(minL[right], left);
            map.put(new Node(left, right), true);
        }
        
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            
            if(map.containsKey(new Node(left, right))) {
                sb.append("1").append("\n");
                continue;
            }
            
            int max = maxR[left];
            int min = minL[right];
            
            if(max >= right && min <=left) {
                sb.append("2").append("\n");
            } else {
                sb.append("-1").append("\n");
            }
        }
        
        System.out.print(sb);
        
    }
    
    static class Node {
        int left;
        int right;
        
        Node (int left, int right){
            this.left = left;
            this.right = right;
        }
        
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            Node node = (Node) object;
            return left == node.left && right == node.right;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }
}
