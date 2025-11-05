import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        HashSet<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            A.add(a);
        }
        
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            int b = Integer.parseInt(st.nextToken());
            B.add(b);
        }
        
        Iterator<Integer> it = A.iterator();
        int cnt = 0;
        while(it.hasNext()){
            Integer next = it.next();
            if(!B.contains(next)){
                cnt++;
            }
        }
        
        it = B.iterator();
        while(it.hasNext()){
            Integer next = it.next();
            if(!A.contains(next)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
