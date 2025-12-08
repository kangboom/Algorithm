import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main { // 백준 제출 시 class 이름은 Main이어야 합니다.
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        
        int tLength = T.length();
        
        // Stack 대신 StringBuilder를 사용하여 스택처럼 활용
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            sb.append(ch);
            
            // sb의 길이가 폭발 문자열보다 길거나 같아지면 검사 시작
            if (sb.length() >= tLength) {
                boolean isSame = true;
                
                // 뒤에서부터 폭발 문자열과 일치하는지 확인
                for (int j = 0; j < tLength; j++) {
                    char ch1 = sb.charAt(sb.length() - tLength + j);
                    char ch2 = T.charAt(j);
                    
                    if (ch1 != ch2) {
                        isSame = false;
                        break;
                    }
                }
                
                // 폭발 문자열과 일치하면 뒤에서부터 길이만큼 삭제 (pop 효과)
                if (isSame) {
                    sb.delete(sb.length() - tLength, sb.length());
                }
            }
        }
        
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }
    }
}