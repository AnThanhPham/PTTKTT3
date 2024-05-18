public class Student implements Comparable<Student>{
    private String ho;

    private String hodem;

    private String ten;

    private String ns;

    private String ma;

    private Double dtb;
    
    private String sdt;
    
    private ST<Mon,Double> st = new ST<>();
    
    public ST<Mon,Double> getBdiemSV(){
        return st;
    }
    
    public void setBdiemSV(ST<Mon,Double> o){
        this.st = o;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

    public Student(String other){
        String[] arr = other.split("\\s+");
        ma = arr[arr.length-1];
        ns = arr[arr.length-2];
        ten = arr[arr.length-3];
        hodem = arr[arr.length-4];
        StringBuilder tmp = new StringBuilder("");
        for(int i=0;i<arr.length-4;i++){
            tmp.append(arr[i]);
        }
        ho = tmp.toString();
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
        System.out.println("Danh sach diem cac mon cua "+ho + " " + hodem + " " + ten + " ");
        for (Mon s : st.keys()){
            System.out.println(s + "\tDiem tb:" + st.get(s));
        }
        System.out.println("");
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
        return ho + " " + hodem + " " + ten + " " + ns + " " + ma + " " + dtb + "\n";
    }

    // input "toan31.csv","van21.csv","dia32.csv"
    public static void main(String[] args) {
        Student[] a = new Student[2];
        a[0] = new Student("Pham Thanh An 24/03/2004 221230727");
        a[1] = new Student("Hoang Trong Van 24/03/2004 221230726");

        Mon giaiTich = new Mon("Giai tich",3,1);
        Mon ktct = new Mon("Kinh te chinh tri",3,2);
        Mon ktlt = new Mon("Ki thuat lap trinh",2,1);
        Mon nlhdh = new Mon("Nguyen li he dieu hanh",2,3);
        Mon oop = new Mon("Lap trinh huong doi tuong",3,2);
        Mon ctdl = new Mon("Cau truc du lieu va giai thuat",3,3);

        a[0].nhapDiemMonSv(giaiTich,3.0);
        a[0].nhapDiemMonSv(nlhdh,3.5);
        a[0].nhapDiemMonSv(ktct,3.6);
        a[0].nhapDiemMonSv(oop,3.2);

        a[1].nhapDiemMonSv(giaiTich,3.0);
        a[1].nhapDiemMonSv(nlhdh,3.5);
        a[1].nhapDiemMonSv(ktct,3.6);
        a[1].nhapDiemMonSv(oop,3.2);
        a[1].nhapDiemMonSv(ctdl,3.6);
        a[1].nhapDiemMonSv(ktlt,2.8);
        
        a[0].inHoSoSinhVien();
        a[1].inHoSoSinhVien();

        System.out.println("Điểm trung bình của sinh viên "+a[0].getTen()+" "+a[0].getDtb());
        System.out.println("Điểm trung bình của sinh viên "+a[1].getTen()+" "+a[1].getDtb());

        System.out.println("Điểm trung bình học kì 1 của sinh viên "+a[0].getTen()+" "+a[0].tinhDiemTBC(1));
        System.out.println("Điểm trung bình học kì 2 của sinh viên "+a[1].getTen()+" "+a[1].tinhDiemTBC(2));
    }

}
