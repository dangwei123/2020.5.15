二叉树最近公共祖先
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
	
二叉搜索树最近公共祖先
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if(root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }

括号生成
List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        back(0,0,n,"");
        return res;
    }
    private void back(int left,int right,int n,String s){
        if(left<right||left>n||right>n) return;
        if(left==n&&right==n){
            res.add(s);
            return;
        }
        back(left+1,right,n,s+"(");
        back(left,right+1,n,s+")");
    }
	
子集(不包含重复元素)
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        back(nums,0,nums.length,new ArrayList<>());
        return res;
    }
    private void back(int[] nums,int begin,int n,List<Integer> row){
        res.add(new ArrayList(row));
        if(begin==n) return;
        for(int i=begin;i<n;i++){
            row.add(nums[i]);
            back(nums,i+1,n,row);
            row.remove(row.size()-1);
        }
    }
}

子集(含有重复元素)
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        back(nums,0,nums.length,new ArrayList<>(),new boolean[nums.length]);
        return res;
    }
    private void back(int[] nums,int begin,int n,List<Integer> row,boolean[] isVisited){
        res.add(new ArrayList(row));
        if(begin==n) return;
        for(int i=begin;i<n;i++){
            if(isVisited[i]) continue;
            if(i>0&&nums[i]==nums[i-1]&&!isVisited[i-1]){
                continue;
            }
            row.add(nums[i]);
            isVisited[i]=true;
            back(nums,i+1,n,row,isVisited);
            row.remove(row.size()-1);
            isVisited[i]=false;
        }
    }
}

全排列(没有重复元素)
class Solution {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        back(nums,0,nums.length,new ArrayList<>(),new boolean[nums.length]);
        return res;
    }
    private void back(int[] nums,int n,int len,List<Integer> row,boolean[] isVisited){
        if(n==len){
            res.add(new ArrayList(row));
            return;
        }
        for(int i=0;i<len;i++){
            if(!isVisited[i]){
                row.add(nums[i]);
                isVisited[i]=true;
                back(nums,n+1,len,row,isVisited);
                row.remove(row.size()-1);
                isVisited[i]=false;
            }
        }
    }
}


格雷编码
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res=new ArrayList<>();
        res.add(0);
        for(int i=1;i<(1<<n);i++){
            int next=i^(i>>1);
            res.add(next);
        }
        return res;
    }
}

买卖股票的最佳时机 II
class Solution {
    public int maxProfit(int[] prices) {
        int dp0=0;
        int dp1=Integer.MIN_VALUE;
        for(int price:prices){
            int pre=dp0;
            dp0=Math.max(dp0,dp1+price);
            dp1=Math.max(dp1,dp0-price);
        }
        return dp0;
    }
}

不同路径
public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for(int i=0;i<n;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
     }
	 


