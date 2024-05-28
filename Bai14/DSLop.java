import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class DSLop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DSLop
{
    private static ArrayList<Student> arr = new ArrayList<>();
    
    public DSLop(String[] args)
    {
        String filename  = args[0];
        In in = new In(filename);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Student tmp = new Student(fields);
            arr.add(tmp);
        }
    }
    
    public static void inDanhSachLop(){
        System.out.println("Danh sach lop:");
        for(Student x : arr){
            System.out.print(x);
        }
        System.out.println("");
    }
    
    public static void inDanhSachDongHuong(String ten){
        System.out.println("Danh sach dong huong:");
        for(Student x : arr){
            if(x.getQue().equals(ten)){
                System.out.print(x);
            }
        }
        System.out.println("");
    }
    
    public static void inDanhSachSinhVien(Double diem){
        System.out.println("Danh sach sinh vien theo diem >= "+diem+" theo thu tu giam dan:");
        Collections.sort(arr,new Student.DTBC());
        for(Student x : arr){
            if(x.getDtb() >= diem){
                System.out.print(x);
            }
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        // input : "input_student.csv","Ha Noi","3.0"
        DSLop ds = new DSLop(args);
        String tenQue  = args[1];
        Double diem = Double.parseDouble(args[2]);
        
        ds.inDanhSachLop();
        ds.inDanhSachDongHuong(tenQue);
        ds.inDanhSachSinhVien(diem);
    }
}
