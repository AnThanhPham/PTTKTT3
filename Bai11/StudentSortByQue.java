import java.util.Collections;
import java.io.File;
import java.util.ArrayList;
public class StudentSortByQue
{
    public StudentSortByQue(){}

    public static void main(String[] args) {
        // input : "Ha Noi","input_student.csv","toan31.csv","van21.csv","dia32.csv"
        String quecantim  = args[0];
        String filename  = args[1];
        In in = new In(filename);
        
        ST<Student,ST<Mon,Double>> st = new ST<Student,ST<Mon,Double>>();
        
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Student tmp = new Student(fields);
            st.put(tmp, new ST<Mon,Double>());
        }
        
        for(int i=2;i<args.length;i++){
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
        
        ArrayList<Student> listStuByQue = new ArrayList<>();
        for(Student student : st.keys()){
            System.out.println(student.toString());
            System.out.println("Danh sach diem cac mon cua student co ma "+student.getMa()+":");
            ST<Mon,Double> cacmon = st.get(student);
            /*student.setBdiemSV(cacmon);*/
            if(student.getQue().equalsIgnoreCase(quecantim)){
                listStuByQue.add(student);
            }
            for(Mon monhoc : cacmon.keys()){
                System.out.println(monhoc+" diem:"+cacmon.get(monhoc));
            }
            System.out.println("");
        }
        
        System.out.println("\n\nDanh sach lop sap xep theo que:");
        Collections.sort(listStuByQue,new Student.SortByAddress());
        for(Student x : listStuByQue){
            System.out.println(x);
        }
        System.out.println("");
    }
}
