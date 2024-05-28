import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class KnapsackApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KnapsackApp {
    public static void main(String[] args) {
        Item[] items = new Item[]{
            new Item("do1",1,1),
            new Item("do2",6,2),
            new Item("do3",18,5),
            new Item("do4",22,6),
            new Item("do5",28,7)
        };
        ArrayList<Item> arr = new ArrayList<Item>(Arrays.asList(items));
        KnapsackImp balo = new KnapsackImp(arr, 11);
        System.out.println("");
        System.out.println("can nang cua balo " + balo.getTotalProfit());
        System.out.println("dung tich cua balo " + balo.getRealWeight());
    }
}

