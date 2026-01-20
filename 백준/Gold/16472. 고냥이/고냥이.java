import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        
        int[] cnt = new int[26]; // 알파벳 갯수
        int kind = 0; // 알파벳 종류
        int left = 0;
        int right = 0;
        int ans = 0;
        
        while (right < str.length()) {
            char now = str.charAt(right);
            if (cnt[now - 'a'] == 0) {
                kind++;
            }
            cnt[now - 'a']++;
            
            // 종류 초과
            while (kind > N) {
                char ch = str.charAt(left);
                if (--cnt[ch - 'a'] == 0) {
                    kind--;
                }
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        System.out.println(ans);
    }
}

