import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;

public class FileSearch
{
    private static FileFrequencyIndex ffi;
    // luu tru cac file day chua bao nhieu tong so tu can tim
    private static ST<File,Integer> st = new ST<>();
    // check xem co file nao thoa man khong
    private static boolean searchSuccess = true;
    
    public FileSearch(){
        
    }
    
    public void query(String[] words){
        // Loc ra cac tu khac nhau trong chuoi query
        HashSet<String> myWords = new HashSet<String>();
        Collections.addAll(myWords, words);     
        
        // bien i de khoi tao st lan chay dau tien
        int i = 0;
        
        // noi luu tru nhung tu nao co o trong file nao va file day chua bao nhieu tu day
        ST<String, ST<File,Integer>> st2 = ffi.getST();
        
        // la noi luu tru de check xem cac file co chua day du cac tu can query khong
        ST<File,Integer> checkFullWord = new ST<File,Integer>();
        
        for(String word : myWords){
            if(!st2.contains(word)){
                searchSuccess = false;
                break;
            }else {
                ST<File,Integer> tmp = st2.get(word);
                if(i==0){
                    for(File file : tmp.keys()){
                        checkFullWord.put(file,1);
                        st.put(file,tmp.get(file));
                    }
                    i++;
                }else {
                    for(File file : tmp.keys()){
                        if(!st.contains(file)) continue;
                        checkFullWord.put(file,checkFullWord.get(file)+1);
                        Integer oldCount =  st.get(file);
                        Integer newCount =  tmp.get(file);
                        st.put(file,oldCount+newCount);
                    }
                }
            }
        }
        // loc ra nhung ra file chua tat ca cac tu query tai trong lan dau bien con dong lai
        for(File file : checkFullWord.keys()){
            if(checkFullWord.get(file) != myWords.size()){
                st.delete(file);
            }
        }
    }
    
    public static LinkedHashMap<File,Integer> sortByValue(ST<File, Integer> st) {
        // Create a Comparator to compare Integer values
        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                Integer value1 = st.get(file1);
                Integer value2 = st.get(file2);
                // Compare the Integer values
                return value2.compareTo(value1);
            }
        };
        LinkedHashMap<File,Integer> res = new LinkedHashMap<File,Integer>();
        // Create a list of files from the ST
        List<File> fileList = new ArrayList<>();
        for(File file : st.keys()){
            fileList.add(file);
        }
        // Sort the list of files using the Comparator
        Collections.sort(fileList, comparator);
        // Create a new ST to store sorted key-value pairs
        ST<File, Integer> sortedST = new ST<>();
        
        // Add sorted key-value pairs to the new ST
        for (File file : fileList) {
            res.put(file, st.get(file));
        }
        
        // Return the sorted ST
        return res;
    }
    
    public static void inKetQua(){
        if(!searchSuccess){
            System.out.println("Khong co file nao thoa man");
        }else {
            System.out.println("Ket qua cac file sau khi da loc");
            for(File file : sortByValue(st).keySet()){
                Integer frequencyWord = st.get(file);
                System.out.println(file.getName() + "\t" +frequencyWord);
            }
        }
    }
    
    public static void main(String[] args){
        // input "ex1.txt","ex2.txt","ex3.txt","ex4.txt"
        FileSearch fs = new FileSearch();
        ffi = new FileFrequencyIndex(args);
        String word = "it the age age";
        String[] words = word.split("\\s+");
        fs.query(words);
        fs.inKetQua();
    }
}
