import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
    
    static Info[] tree;
    static int[] arr;
    static int N;
    static int M;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int level = (int)Math.ceil(Math.log(N)/Math.log(2));
        arr = new int[N+1];
        tree = new Info[(int)Math.pow(2,level+1)];
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        setTree(1, 1, N);
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            Info info = search(1, a, b, 1, N);
            sb.append(info.min).append(" ").append(info.max).append("\n");
        }
        System.out.println(sb);
    }
    
    static Info search(int idx, int left, int right, int start, int end){
        if(left == start && right == end){
            return tree[idx];
        }
        
        int mid = (start+end)/2;
        // 왼쪽에 포함
        if(right<=mid){
            return search(idx*2, left, right, start, mid);
        } else if(left > mid){ // 오른쪽에 포함
            return search(idx*2+1, left, right, mid+1, end);
        } else {
            Info searchL = search(idx * 2, left, mid, start, mid);
            Info searchR = search(idx * 2 + 1, mid + 1, right, mid + 1, end);
            
            return new Info(Math.min(searchL.min, searchR.min), Math.max(searchL.max, searchR.max));
        }
    }
    
    static Info setTree(int idx, int left, int right){
        if(left == right){
            return tree[idx] = new Info(arr[left], arr[left]);
        }
        
        int mid = (left+right)/2;
        Info leftChild = setTree(idx*2, left, mid);
        Info rightChild = setTree(idx*2+1, mid+1, right);
        
        return tree[idx] = new Info(Math.min(leftChild.min, rightChild.min), Math.max(leftChild.max, rightChild.max));
    }
    
    static class Info {
        int max;
        int min;
        
        Info(int min, int max){
            this.min = min;
            this.max = max;
        }
    }
}
