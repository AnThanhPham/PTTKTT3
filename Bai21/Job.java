import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

// Data structure to store a Job
class Job implements Comparable<Job>{
    int start, finish, profit;

    Job(int start, int finish, int profit) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
    }
    //public int compareTo (Job x, Job y){
    //return Double.compare(x.finish, y.finish);
    //}
    public int compareTo (Job y){
        return Double.compare(this.finish, y.finish);
    }
    
    public int getProfit(){
        return profit;
    }
    
    @Override
    public String toString(){
        return "Job [start=" + start + ", finish=" + finish + ", profit=" + profit + "]";
    }

    // Function to perform binary search on the given jobs which are sorted by finish time.
    // The function returns index of the last job which doesn't conflict with the given job
    // i.e. whose finish time is less than or equal to the start time of the given job.
    
    // tim ra vi tri cuoi cung cua cong viec khong bi xung dot voi cong viec i 
    // (thu tu sap xep cong viec theo thoi gian ket thuc) Neu khong tim thay tra ve -1
    public static int findLastNonConflictingJob(List<Job> jobs, int n)
    {
        // search space
        int low = 0;
        int high = n;

        // iterate till search space is not exhausted
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (jobs.get(mid).finish <= jobs.get(n).start) {
                if (jobs.get(mid + 1).finish <= jobs.get(n).start) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            }
            else {
                high = mid - 1;
            }
        }

        // return negative index if no non-conflicting job is found
        return -1;
    }

    // Function to find the maximum profit of non-overlapping jobs using DP
    public static int maxProfit(List<Job> jobs,ArrayList<Job>[] ans)
    {
        // sort jobs in increasing order of their finish times
        //Collections.sort(jobs, (x, y) -> x.finish - y.finish);
        Collections.sort(jobs);
        // get number of jobs
        int n = jobs.size();

        // construct an lookup table where the i'th index stores the maximum profit
        // for first i jobs
        int[] maxProfit = new int[n];

        // maximum profit gained by including the first job
        maxProfit[0] = jobs.get(0).profit;
        
        ans[0].add(jobs.get(0));

        // fill maxProfit[] table in bottom-up manner from the second index
        for (int i = 1; i < n; i++)
        {
            // find the index of last non-conflicting job with current job
            int index = findLastNonConflictingJob(jobs, i);

            // include the current job with its non-conflicting jobs
            int incl = jobs.get(i).profit;
            if (index != -1) {
                incl += maxProfit[index];
            }

            // store the maximum profit by including or excluding current job
            maxProfit[i] = Math.max(incl, maxProfit[i - 1]);
            
            // neu cong viec hien thoi ket hop voi cac cv o vi tri index tao max
            if(incl > maxProfit[i - 1]){
                if(index != -1){
                    // lay ra cac cv max loi nhuan da tron truoc do o vi tri index
                    for(Job x : ans[index]){
                        ans[i].add(x);
                    }
                }
                // add them cong viec hien thoi
                ans[i].add(jobs.get(i));
            }else {
                for(Job x : ans[i-1]){
                    ans[i].add(x);
                }
            }
        }
        
        // return maximum profit
        return maxProfit[n - 1];
    }

    public static void main(String[] args)
    {
        // List<Job> jobs = Arrays.asList(new Job( 0, 6, 60 ),
                                            // new Job( 1, 4, 30 ),
                                            // new Job( 3, 5, 10 ),
                                            // new Job( 5, 7, 30 ),
                                            // new Job( 5, 9, 50 ),
                                            // new Job( 7, 8, 10 ));
        List<Job> jobs = Arrays.asList(new Job( 0, 6, 60 ),
                                            new Job( 1, 4, 5 ),
                                            new Job( 3, 5, 10 ),
                                            new Job( 5, 7, 30 ),
                                            new Job( 5, 9, 50 ),
                                            new Job( 7, 8, 10 ));
        // bien ans giup de luu cac cong viec de dat duoc loi nhuan lon nhat
        ArrayList<Job>[] ans = new ArrayList[jobs.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = new ArrayList<>();
        }
        
        System.out.println("The maximum profit is " + maxProfit(jobs,ans));
        
        System.out.println("Danh sach cac cong viec chon:");
        for(Job x : ans[ans.length-1]){
            System.out.println(x);
        }
        // System.out.println("");
        // for (int i = 0; i < ans.length; i++) {
            // for(Job x : ans[i]){
                // System.out.print(x+" ");
            // }
            // System.out.println("");
        // }
    }
}