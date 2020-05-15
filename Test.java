整数反转
class Solution {
    public int reverse(int x) {
        boolean flag=false;
        if(x<0){
            if(x==Integer.MIN_VALUE) return 0;
            flag=true;
            x=-x;
        }
        int res=0;
        while(x!=0){
            if(!flag&&(res>Integer.MAX_VALUE/10||(res==Integer.MAX_VALUE/10&&x>7))){
                return 0;
            }
            if(flag&&(res>Integer.MAX_VALUE/10||(res==Integer.MAX_VALUE/10&&x>8))){
                return 0;
            }
            res=res*10+x%10;
            x/=10;
        }
        return flag?-res:res;
    }
}

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x<10) return true;
        int help=1;
       int tmp=x;
        while(tmp>9){
            help*=10;
           tmp/=10;
        }
        while(x!=0){
            if(x/help!=x%10){
                return false;
            }
            x=(x%help)/10;
            help/=100;
        }
        return true;
    }
	
多数元素
public int majorityElement(int[] nums) {
        int res=0;
        int count=0;
        for(int num:nums){
            if(count==0){
                res=num;
                count=1;
            }else if(num==res){
                count++;
            }else{
                count--;
            }
        }
        return res;
    }
排序链表
public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode fast=head;
        ListNode slow=head;
        ListNode pre=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            pre=slow;
            slow=slow.next;
        }
        pre.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(slow);
        return merge(left,right);
    }
    
    private ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
	
搜索旋转排序数组
public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }
            if(nums[mid]>=nums[left]){
                if(target>=nums[left]&&target<=nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else {
                if(target>=nums[mid]&&target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return -1;
    }
	
数组中第k大元素
class Solution {
    private int res;
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1,nums.length-k);
        return res;
    }
    private void quickSort(int[] nums,int left,int right,int k){
        if(left>right) return;
        int pivot=partition(nums,left,right);
        if(pivot==k){
            res=nums[pivot];
            return;
        }else if(pivot<k){
            quickSort(nums,pivot+1,right,k);
        }else{
            quickSort(nums,left,pivot-1,k);
        }
    }
    private int partition(int[] nums,int left,int right){
        int i=left;
        int j=right;
        int pivot=nums[left];
        while(i<j){
            while(i<j&&nums[j]>=pivot){
               j--;
            }
            nums[i]=nums[j];
            while(i<j&&nums[i]<=pivot){
               i++;
            }
            nums[j]=nums[i];
        }
        nums[i]=pivot;
        return i;
    }
    
}

二叉搜索树中第K小的元素
class Solution {
    private int res;
    private int num;
    public int kthSmallest(TreeNode root, int k) {
        num=k;
        inorder(root);
        return res;
    }
    private void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        if(--num==0){
            res=root.val;
            return;
        }
        inorder(root.right);
    }
}

二叉树中的最大路径和
class Solution {
    private int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null) return 0;
        getmax(root);
        return max;
    }
    private int getmax(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=getmax(root.left);
       
        int right=getmax(root.right);
        
        int tmp=Math.max(left,0)+Math.max(right,0)+root.val;
        max=Math.max(max,tmp);
        return root.val+Math.max(Math.max(left,right),0);
    }
}