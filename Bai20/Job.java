import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

// Data structure to store a Job
class Job implements Comparable<Job>{
    int start, finish;

    Job(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    public int compareTo (Job y){
        return Double.compare(this.finish, y.finish);
    }
    
    public int getStart(){
        return start;
    }
    
    public int getFinish(){
        return finish;
    }
    
    @Override
    public String toString() {
    	return "Job [start=" + start + ", finish=" + finish + "]";
    }
    

    public static void main(String[] args)
    {
        List<Job> jobs = Arrays.asList(new Job( 0, 6),
                                            new Job( 1, 4),
                                            new Job( 3, 5),
                                            new Job( 5, 7),
                                            new Job( 5, 9),
                                            new Job( 7, 8));
                                            

        Collections.sort(jobs);
        System.out.println("Danh sach lich trinh khi sap xep theo thoi gian ket thuc:");
        for(Job x : jobs){
            System.out.println(x);
        }
        int ans = 1;
        ArrayList<Job> res = new ArrayList<>();
        res.add(jobs.get(0));
        for(int i=1;i<jobs.size();i++){
            if(jobs.get(i).getStart() >= res.get(res.size()-1).getFinish()){
                ans++;
                res.add(jobs.get(i));
            }
        }
        System.out.println("So luong toi da max la:"+ans);
        System.out.println("Danh sach lich trinh bao gom:");
        for(Job x : res){
            System.out.println(x);
        }
    }
}