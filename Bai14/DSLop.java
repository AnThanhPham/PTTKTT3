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
    
    public DSLop()
    {
    }
    
    public static void inDanhSachLop(){
        System.out.println("Danh sach lop:\n");
        for(Student x : arr){
            System.out.println(x);
        }
    }
    
    public static void inDanhSachDongHuong(String ten){
        System.out.println("Danh sach dong huong:\n");
        for(Student x : arr){
            if(x.getQue().equals(ten)){
                System.out.println(x);
            }
        }
    }
    
    public static void inDanhSachSinhVien(Double diem){
        System.out.println("Danh sach sinh vien theo diem >= "+diem+" theo thu tu giam dan:\n");
        Collections.sort(arr,new Student.DTBC());
        for(Student x : arr){
            if(x.getDtb() >= diem){
                System.out.println(x);
            }
        }
    }
    
    public static void main(String[] args) {
        // input : "input_student.csv","Ha Noi","3.0"
        String filename  = args[0];
        String tenQue  = args[1];
        Double diem = Double.parseDouble(args[2]);
        
        In in = new In(filename);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Student tmp = new Student(fields);
            arr.add(tmp);
        }
        inDanhSachLop();
        System.out.println("");
        inDanhSachDongHuong(tenQue);
        inDanhSachSinhVien(diem);
    }
}
