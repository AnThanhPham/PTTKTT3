import java.io.*;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class HashMapSinhVien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HashMapSinhVien
{
    private static int i=1;
    
    private static int demSoLuongChuSo(int u){
        int res = 0;
        while(u/10 != 0){
            res++;
            u/=10;
        }
        return res++;
    }
    
    
    public static String generateStudentID(){
        StringBuilder IdSchool = new StringBuilder("GTVT");
        IdSchool.append("0".repeat(6-demSoLuongChuSo(i)));
        IdSchool.append(i+"");
        i++;
        return IdSchool.toString();
    }
    
    public static void main(String[] args) {
        // input : "input_student.csv"
        String filename  = args[0];
        In in = new In(filename);
        
        HashMap<Student,String> hs = new HashMap<>();
        ArrayList<Student> arr = new ArrayList<>();
        
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Student tmp = new Student(fields);
            arr.add(tmp);
        }
        
        Collections.sort(arr,new Student.TenHd());
        for(Student x : arr){
            x.setMa(generateStudentID());
            hs.put(x,x.getMa());
        }
        System.out.println("Danh sach sinh vien sau khi tao la:\n");
        for(Student x : hs.keySet()){
            System.out.println(x);
        }
    }
}
