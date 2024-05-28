import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.util.TreeMap;

public class DSLop
{
    private static ST<Student,ST<Mon,Double>> st = new ST<Student,ST<Mon,Double>>();

    public DSLop(String[] args)
    {
        String filename  = args[0];
        In in = new In(filename);
        
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Student tmp = new Student(fields);
            st.put(tmp, new ST<Mon,Double>());
        }
        
        for(int i=1;i<args.length;i++){
            File file = new File(args[i]);
            Mon mon = new Mon(args[i]);
            In in1 = new In(file);
            while(!in1.isEmpty()){
                String line = in1.readLine();
                String[] fields = line.split(";");
                for(Student student : st.keys()){
                    if(student.getMa().equals(fields[0])){
                        st.get(student).put(mon,Double.parseDouble(fields[1]));
                        student.nhapDiemMonSv(mon,Double.parseDouble(fields[1]));
                        break;
                    }
                }
            }
        }
    }
    
    public static void xetThiDuaHocKy(int hocKy){
        System.out.println("\n\nXet thi dua hoc ki:");
        TreeMap<Double,Student> tree = new TreeMap<>();
        for(Student x : st.keys()){
            double diem = x.tinhDiemTBC(hocKy);
            tree.put(diem,x);
        }
        System.out.println("10 nguoi co TBC cao nhat hoc ky "+hocKy+":");
        int i = 0;
        for(Double diem : tree.descendingKeySet()){
            if(i>=10){
                break;
            }else {
                System.out.println("Ho va ten:"+tree.get(diem).getHoVaTen()+" diem :"+diem);
            }
            i++;
        }
    }
    
    public static void danhSachDiemCuaTungSinhVien(){
        for(Student student : st.keys()){
            System.out.println(student.toString());
            System.out.println("Danh sach diem cac mon cua student co ma "+student.getMa()+":");
            ST<Mon,Double> cacmon = st.get(student);
            student.setBdiemSV(cacmon);
            for(Mon monhoc : cacmon.keys()){
                System.out.println(monhoc+" diem:"+cacmon.get(monhoc));
            }
            System.out.println("");
        }
    }
    
    public void DanhSachLopTheoTen(){
        ArrayList<Student> listStu = new ArrayList<>();
        for(Student x : st.keys()){
            listStu.add(x);
        }
        System.out.println("\n\nDanh sach lop sap xep theo ten:");
        Collections.sort(listStu,new Student.TenHd());
        for(Student x : listStu){
            System.out.print(x);
        }
        System.out.println("");
    }
    
    public void DanhSachLopTheoNgaySinh(){
        ArrayList<Student> listStu = new ArrayList<>();
        for(Student x : st.keys()){
            listStu.add(x);
        }
        System.out.println("\n\nDanh sach lop sap xep theo ngay sinh tu gia den tre:");
        Collections.sort(listStu,new Student.Ns());
        for(Student x : listStu){
            System.out.print(x);
        }
        System.out.println("");
    }
    
    public void DanhSachLopTheoQue(String quecantim){
        ArrayList<Student> listStuByQue = new ArrayList<>();
        for(Student student : st.keys()){
            if(student.getQue().equalsIgnoreCase(quecantim)){
                listStuByQue.add(student);
            }
        }
        System.out.println("\n\nDanh sach lop sap xep theo que:");
        Collections.sort(listStuByQue,new Student.SortByAddress());
        for(Student x : listStuByQue){
            System.out.print(x);
        }
        System.out.println("");
    }
    
    // input : "input_student.csv","toan31.csv","van21.csv","dia32.csv"
    public static void main(String[] args){
        DSLop ds = new DSLop(args);
        
        ds.danhSachDiemCuaTungSinhVien();
        
        ds.xetThiDuaHocKy(1);
        
        ds.DanhSachLopTheoTen();
        
        ds.DanhSachLopTheoNgaySinh();
        
        String quecantim = "Ha Noi";
        ds.DanhSachLopTheoQue(quecantim);
    }
}
