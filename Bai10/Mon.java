
/**
 * Write a description of class Mon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mon implements Comparable<Mon>{
    private String ten;
    private int soTinChi;
    private int kyThu;
    
    public Mon() {
    }
    
    public Mon(String other) {
        String[] arr = other.split("\\.");
        this.kyThu = arr[0].charAt(arr[0].length()-1) - '0';
        this.soTinChi = arr[0].charAt(arr[0].length()-2) - '0';
        this.ten = arr[0].substring(0, arr[0].length()-2);
    }
    
    public Mon(String ten, int soTinChi, int kyThu) {
        this.ten = ten;
        this.soTinChi = soTinChi;
        this.kyThu = kyThu;
    }
    
    @Override
    public int compareTo(Mon o) {
        return ten.compareTo(o.getTen());
    }
    
    @Override
    public String toString() {
        return "Mon [ten=" + ten + ", soTinChi=" + soTinChi + ", kyThu=" + kyThu + "]";
    }
    
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public int getSoTinChi() {
        return soTinChi;
    }
    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
    public int getKyThu() {
        return kyThu;
    }
    public void setKyThu(int kyThu) {
        this.kyThu = kyThu;
    }
}

