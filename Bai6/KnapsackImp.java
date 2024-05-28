import java.util.ArrayList;
/**
 * Write a description of class KnapsackImp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KnapsackImp {
    private int W;
    private int realWeight = 0;
    private int totalProfit = 0;
    private Bag<Item> bag = new Bag<Item>();

    public KnapsackImp() {
    }
    
    public KnapsackImp(ArrayList<Item> items,int W) {
        this.W = W;
        int N = items.size();
        
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];
        
        for(int n=1;n <= N;n++){
            for(int w=1;w <= W;w++){
                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (items.get(n-1).getWeight() <= w) option2 = items.get(n-1).getProfit() + opt[n-1][w-items.get(n-1).getWeight()];

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }
        
        // determine which items to take
        boolean[] take = new boolean[N+1];
        for (int n = N, w = W; n > 0; n--) {
            if (sol[n][w]) {
                take[n] = true;
                w = w - items.get(n-1).getWeight();
                this.realWeight += items.get(n-1).getWeight();
                this.totalProfit += items.get(n-1).getProfit();
            }
            else {
                take[n] = false;
            }
        }
        
        for(int n=1;n<=N;n++){
            System.out.println(n + "\t" + items.get(n-1).getProfit() + "\t" + items.get(n-1).getWeight() + "\t" + take[n]);
        }
    }
    
    public int getRealWeight() {
        return realWeight;
    }

    public void setRealWeight(int realWeight) {
        this.realWeight = realWeight;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(int totalProfit) {
        this.totalProfit = totalProfit;
    }
    
}
