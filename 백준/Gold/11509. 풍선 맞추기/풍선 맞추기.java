import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> arrow = new HashMap<>();
        
        int ans = 0;
        for(int i=0; i<N; i++){
            int h = heights[i];
            if(arrow.getOrDefault(h, 0) > 0){
                arrow.put(h, arrow.get(h) - 1);
            } else {
                ans++;
            }
            arrow.put(h-1, arrow.getOrDefault(h-1, 0) + 1);
        }
        System.out.println(ans);
    }
}
