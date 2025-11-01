import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        MaxHeap mapHeap = new MaxHeap();
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                sb.append(mapHeap.pop());
                sb.append("\n");
                continue;
            }
            mapHeap.push(num);
        }
        
        System.out.println(sb);
    }
    
    static class MaxHeap {
        int[] arr = new int[100_001];
        int size = 0;
        
        public void push(int x){
            arr[++size] = x;
            int current = size;
            while(current > 1){
                // 부모와 비교
                int parent = current/2;
                if(arr[parent] >= arr[current]) break;
                swap(current, parent);
                
                current = parent;
            }
        }
        
        public int pop(){
            if(size == 0){
                return 0;
            }
            int result = arr[1];
            swap(1, size--);
            
            int current = 1;
            while(2*current <= size){
                int left = 2*current;
                int right = 2*current+1;
                int child = left;
                if(right <= size && arr[left] < arr[right]){
                    child = right;
                }
                if(arr[current] >= arr[child]) break;
                swap(current, child);
                current = child;
            }
            
            return result;
        }
        
        private void swap(int a, int b){
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
    }
}
