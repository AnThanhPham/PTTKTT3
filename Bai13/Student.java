import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.util.TreeMap;

public class Student implements Comparable<Student>{
    private String ho;

    private String hodem;

    private String ten;

    private String ns;

    private String ma;

    private Double dtb;
    
    private String que;
    
    private ST<Mon,Double> st = new ST<>();
    
    public ST<Mon,Double> getBdiemSV(){
        return st;
    }
    
    public void setBdiemSV(ST<Mon,Double> o){
        this.st = o;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getHodem() {
        return hodem;
    }

    public void setHodem(String hodem) {
        this.hodem = hodem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }
    
    public String getHoVaTen() {
        return ho+" "+hodem+" "+ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Double getDtb() {
        return dtb;
    }

    public void setDtb(Double dtb) {
        this.dtb = dtb;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }
    
    public Student(){
        
    }
    
    public Student(String []fields){
        this.ten = fields[2];
        this.que = fields[fields.length-1];
        this.ho = fields[0];
        this.hodem = fields[1];
        this.ns = fields[3];
        this.ma = fields[4];
    }
    
    public double tinhDiemTBC(int kythu){
        double tongtinchi = 0;
        double tongdiem = 0;
        for (Mon s : st.keys()){
            if(s.getKyThu() == kythu){
                tongdiem += st.get(s) * s.getSoTinChi();
                tongtinchi += s.getSoTinChi();
            }
        }
        return tongdiem / tongtinchi;
    }
    
    public double tinhDiemTBC(){
        double tongtinchi = 0;
        double tongdiem = 0;
        for (Mon s : st.keys()){
            tongdiem += st.get(s) * s.getSoTinChi();
            tongtinchi += s.getSoTinChi();
        }
        dtb = tongdiem / tongtinchi;
        return tongdiem / tongtinchi;
    }
    
    public void nhapDiemMonSv(Mon m,Double diem){
        st.put(m,diem);
    }
    
    public void inHoSoSinhVien(){
        this.dtb = this.tinhDiemTBC();
        StdOut.print(this);
        StdOut.println("Danh sach diem cac mon cua "+ho + " " + hodem + " " + ten + " ");
        for (Mon s : st.keys()){
            StdOut.println(s + "\tDiem tb:" + st.get(s));
        }
        StdOut.println("");
    }

    @Override
    public int compareTo(Student o) {
        return compareTen(this, o);
    }
    
    @Override
    public String toString() {
        return ho + " " + hodem + " " + ten + " " + ns + " " + ma + " " + que + "\n";
    }
    
    public static int compareTen(Student o1, Student o2){
        if(o1.getTen().equals(o2.getTen())){
            if(o1.getHo().charAt(0) == o2.getHo().charAt(0)){
                return o1.getHodem().charAt(0) - o2.getHodem().charAt(0);
            }else {
                if(o1.getHo().charAt(0) == o2.getHo().charAt(0)){
                    return compareNs(o1,o2);
                }else {
                    return o1.getHo().charAt(0) - o2.getHo().charAt(0);
                }
            }
        }else {
            return o1.getTen().compareTo(o2.getTen());
        }
    }

    public static int compareNs(Student o1, Student o2){
        String[] namsinh1 = o1.getNs().split("/");
        String[] namsinh2 = o2.getNs().split("/");
        int ns1 = Integer.parseInt(namsinh1[2]);
        int ns2 = Integer.parseInt(namsinh2[2]);
        int thangsinh1 = Integer.parseInt(namsinh1[1]);
        int thangsinh2 = Integer.parseInt(namsinh2[1]);
        int ngay1 = Integer.parseInt(namsinh1[0]);
        int ngay2 = Integer.parseInt(namsinh2[0]);
        if(ns1!=ns2){
            return ns1 - ns2;
        }else {
            if(thangsinh1!=thangsinh2){
                return thangsinh1 - thangsinh2;
            }else {
                return ngay1 - ngay2;
            }
        }
    }
    
    public static class TenHd implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return compareTen(o1, o2);
        }
    }
    
    public static  class SortByAddress implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            if(o1.getQue().equals(o2.getQue())){
                return compareTen(o1, o2);
            }else {
                return o1.getQue().compareTo(o2.getQue());
            }
        }
    }

    public static class Ns implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            String[] namsinh1 = o1.getNs().split("/");
            String[] namsinh2 = o2.getNs().split("/");
            int ns1 = Integer.parseInt(namsinh1[2]);
            int ns2 = Integer.parseInt(namsinh2[2]);
            int thangsinh1 = Integer.parseInt(namsinh1[1]);
            int thangsinh2 = Integer.parseInt(namsinh2[1]);
            int ngay1 = Integer.parseInt(namsinh1[0]);
            int ngay2 = Integer.parseInt(namsinh2[0]);
            if(ns1!=ns2){
                return ns1 - ns2;
            }else {
                if(thangsinh1!=thangsinh2){
                    return thangsinh1 - thangsinh2;
                }else {
                    return ngay1 - ngay2;
                }
            }
        }
    }

    public static  class MaSv implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getMa().compareTo(o2.getMa());
        }
    }

    public static class DTBC implements  Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            if(o1.getDtb()!= o2.getDtb()){
                return Double.compare(o1.getDtb(),o2.getDtb());
            }else {
                return compareTen(o1,o2);
            }
        }
    }
    
    public static void xetThiDuaHocKy(ST<Student,ST<Mon,Double>> st,int hocKy){
        ST<Student,Double> st2 = new ST<>();
        TreeMap<Double,Student> tree = new TreeMap<>();
        for(Student x : st.keys()){
            double diem = x.tinhDiemTBC(hocKy);
            st2.put(x,diem);
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

    public static void main(String[] args) {
        // input : "input_student.csv","toan31.csv","van21.csv","dia32.csv"
        String filename  = args[0];
        In in = new In(filename);
        
        ST<Student,ST<Mon,Double>> st = new ST<Student,ST<Mon,Double>>();
        
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
        
        ArrayList<Student> listStu = new ArrayList<>();
        for(Student student : st.keys()){
            System.out.println(student.toString());
            System.out.println("Danh sach diem cac mon cua student co ma "+student.getMa()+":");
            ST<Mon,Double> cacmon = st.get(student);
            /*student.setBdiemSV(cacmon);*/
            listStu.add(student);
            for(Mon monhoc : cacmon.keys()){
                System.out.println(monhoc+" diem:"+cacmon.get(monhoc));
            }
            System.out.println("");
        }
        
        System.out.println("\n\nXet thi dua hoc ki:");
        xetThiDuaHocKy(st,1);
        
        System.out.println("\n\nDanh sach lop sap xep theo ten:");
        Collections.sort(listStu,new Student.TenHd());
        for(Student x : listStu){
            System.out.println(x);
        }
        System.out.println("");
        
        
        System.out.println("\n\nDanh sach lop sap xep theo ngay sinh tu gia den tre:");
        Collections.sort(listStu,new Student.Ns());
        for(Student x : listStu){
            System.out.println(x);
        }
        System.out.println("");
    }

}
