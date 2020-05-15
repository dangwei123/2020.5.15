设计哈希集合
class MyHashSet {
    private static class Node{
        int key;
        Node next;
        public Node(){
            
        }
        public Node(int key){
            this.key=key;
        }
    }
    
    private Node[] arr;
    private int size;
    private int capacity;
    /** Initialize your data structure here. */
    public MyHashSet() {
        arr=new Node[10];
        for(int i=0;i<10;i++){
            arr[i]=new Node();
        }
        this.capacity=10;
    }
    
    public void add(int key) {
         resize();
        int index=key%capacity;
        Node cur=arr[index].next;
        while(cur!=null){
            if(cur.key==key) return;
            cur=cur.next;
        }
       
        cur=arr[index];
        Node node=new Node(key);
        node.next=cur.next;
        cur.next=node;
        size++;
        
    }
    public void resize(){
        if(size==capacity){
            int newCapacity=capacity+capacity<<1;
            Node[] newArr=new Node[newCapacity];
            for(int i=0;i<newCapacity;i++){
                newArr[i]=new Node();
            }
            for(int i=0;i<capacity;i++){
                Node next=null;
                Node cur=arr[i].next;
                while(cur!=null){
                    next=cur.next;
                    int index=cur.key%newCapacity;
                    cur.next=newArr[index].next;
                    newArr[index].next=cur;
                    
                    cur=next;
                }
            }
            
            arr=Arrays.copyOf(newArr,newCapacity);
            capacity=newCapacity;
        }
    }
    
    public void remove(int key) {
        int index=key%capacity;
        Node cur=arr[index].next;
        Node pre=arr[index];
        while(cur!=null){
            if(cur.key==key){
                removeNode(pre,cur,arr[index]);
            }
            pre=cur;
            cur=cur.next;
        }
    }
    private void removeNode(Node pre,Node delete,Node parent){
        if(pre==parent){
            parent.next=parent.next.next;
        }else if(delete.next==null){
            pre.next=null;
        }else{
            pre.next=delete.next;
        }
        size--;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index=key%capacity;
        Node cur=arr[index].next;
        while(cur!=null){
            if(cur.key==key) return true;
            cur=cur.next;
        }
        return false;
    }
}

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            Map<Character,Integer> map=new HashMap<>();
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(c>='A'&&c<='Z'){
                    map.put(c,map.getOrDefault(c,0)+1);
                }
            }
            
            for(char c='A';c<='Z';c++){
                //if(map.containsKey(c)){
                    System.out.println(c+":"+map.getOrDefault(c,0));
                //}
            }
        }
    }
}

16进制
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            int res=0;
            int len=s.length();
            for(int i=len-1;i>1;i--){
                char c=s.charAt(i);
                if(c>='0'&&c<='9'){
                    res+=(int)Math.pow(16,len-1-i)*(c-'0');
                }
                if(c>='A'&&c<='F'){
                    res+=(int)Math.pow(16,len-1-i)*(c-'A'+10);
                }
            }
            System.out.println(res);
        }
    }
}

股票问题
public class Solution {
    /**
     * 计算你能获得的最大收益
     * 
     * @param prices Prices[i]即第i天的股价
     * @return 整型
     */
    public int calculateMax(int[] prices) {
        int n=prices.length;
        int[][][] dp=new int[n][3][2];
        for(int i=0;i<n;i++){
            for(int j=2;j>0;j--){
                if(i==0){
                    dp[0][j][0]=0;
                    dp[0][j][1]=-prices[0];
                }else{
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }
                
            }
        }
        return dp[n-1][2][0];
    }
}

设计哈希映射
class MyHashMap {
    private static class Node{
        int key;
        int val;
        Node next;
        public Node(){
            
        }
        public Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
    
    
    private Node[] arr;
    private int size;
    private int capacity;
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr=new Node[10];
        this.capacity=10;
        init(arr,10);
    }
    private void init(Node[] tmp,int n){
        for(int i=0;i<n;i++){
            tmp[i]=new Node();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index=key%capacity;
        Node cur=arr[index].next;
        while(cur!=null){
            if(cur.key==key){
                cur.val=value;
                return;
            }
            cur=cur.next;
        }
        
        resize();
        
        index=key%capacity;
        cur=arr[index];
        Node node=new Node(key,value);
        node.next=cur.next;
        cur.next=node;
        size++;
    }
    private void resize(){
        if(size==capacity){
            int newCapacity=capacity+capacity<<1;
            Node[] newArr=new Node[newCapacity];
            init(newArr,newCapacity);
            for(int i=0;i<capacity;i++){
                Node cur=arr[i].next;
                Node next=null;
                while(cur!=null){
                    next=cur.next;
                    int index=cur.key%newCapacity;
                    cur.next=newArr[index].next;
                    newArr[index].next=cur;
                    cur=next;
                }
            }
            
            arr=Arrays.copyOf(newArr,newCapacity);
            capacity=newCapacity;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index=key%capacity;
        Node cur=arr[index].next;
        while(cur!=null){
            if(cur.key==key){
                return cur.val;
            }
            cur=cur.next;
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index=key%capacity;
        Node cur=arr[index].next;
        Node pre=arr[index];
        while(cur!=null){
            if(cur.key==key){
                removeNode(pre,cur);
            }
            pre=cur;
            cur=cur.next;
        }
    }
    
    private void removeNode(Node pre,Node cur){
        if(cur.next==null){
            pre.next=null;
        }else{
            pre.next=cur.next;
        }
        size--;
    }
}