import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static int[] parents;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        
        int[] planes = new int[P];
        for(int i=0; i<P; i++){
            planes[i] = Integer.parseInt(br.readLine());
        }
        
        parents = new int[G+1];
        for(int i=1; i<=G; i++){
            parents[i] = i;
        }
        
        int cnt = 0;
        for(int g : planes){
            // 그리디 최댓값
            if(parents[g] == g){ // 방문 X
                parents[g] = find(parents[g-1]);
            } else {
                int parent = find(parents[g]); // g의 부모 찾기
                if(parent == 0) break;
                parents[parent] = find(parents[parent-1]);
            }
            cnt++;
        }
        
        System.out.println(cnt);
    }
    
    static int find(int x){
        if(parents[x] != x){
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
}
