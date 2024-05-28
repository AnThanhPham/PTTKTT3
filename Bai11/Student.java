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
        if(this.getTen().equals(o.getTen())){
            if(this.getHo().charAt(0) == o.getHo().charAt(0)){
                return this.getHodem().charAt(0) - o.getHodem().charAt(0);
            }else {
                return this.getHo().charAt(0) - o.getHo().charAt(0);
            }
        }else {
            return this.getTen().compareTo(o.getTen());
        }
    }

    @Override
    public String toString() {
        return ho + " " + hodem + " " + ten + " " + ns + " " + ma + " " + que + "\n";
    }
    
    public String toString2() {
        return ma + " " + ten + " que:" + que + "\n";
    }

    public static int compareTen(Student o1, Student o2){
        if(o1.getTen().equals(o2.getTen())){
            if(o1.getHo().charAt(0) == o2.getHo().charAt(0)){
                return o1.getHodem().charAt(0) - o2.getHodem().charAt(0);
            }else {
                return o1.getHo().charAt(0) - o2.getHo().charAt(0);
            }
        }else {
            return o1.getTen().compareTo(o2.getTen());
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
}
