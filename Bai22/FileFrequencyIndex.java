/******************************************************************************
 *  Compilation:  javac FileIndex.java
 *  Execution:    java FileIndex file1.txt file2.txt file3.txt ...
 *  Dependencies: ST.java SET.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/ex1.txt
 *                https://algs4.cs.princeton.edu/35applications/ex2.txt
 *                https://algs4.cs.princeton.edu/35applications/ex3.txt
 *                https://algs4.cs.princeton.edu/35applications/ex4.txt
 *
 *  % java FileIndex ex*.txt
 *  age
 *   ex3.txt
 *   ex4.txt 
 * best
 *   ex1.txt 
 * was
 *   ex1.txt
 *   ex2.txt
 *   ex3.txt
 *   ex4.txt 
 *
 *  % java FileIndex *.txt
 *
 *  % java FileIndex *.java
 *
 ******************************************************************************/

 
//import java.io.*;
import java.io.File;

/**
 *  The {@code FileIndex} class provides a client for indexing a set of files,
 *  specified as command-line arguments. It takes queries from standard input
 *  and prints each file that contains the given query.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class FileFrequencyIndex { 
    // luu cac tu co o trong file nao file day chua bao nhieu tu do
    private ST<String, ST<File,Integer>> st = new ST<String, ST<File,Integer>>();
    // luu cac tu va so luong cua chung trong mot file
    //private ST<File,ST<String,Integer>> wordInFile = new ST<File,ST<String,Integer>>();
    
    public FileFrequencyIndex() { }
    
    public FileFrequencyIndex(String[] args) { 
        for (String filename : args) {
            File file = new File(filename);
            //wordInFile.put(file,new ST<String,Integer>());
            this.addFile(file);
        }
    }
    
    public void addFile(File file){
        In in = new In(file);
        
        // cac tu va so luong cua chung trong mot file
        //ST<String,Integer> wordPerFile = new ST<String,Integer>();
        
        while (!in.isEmpty()) {
            String word = in.readString();
            // file do co nhung tu nao va tu day co bao nhieu trong file day
            // if(!wordPerFile.contains(word)){
                // wordPerFile.put(word,1);
            // }else {
                // wordPerFile.put(word,wordPerFile.get(word)+1);
            // }
            
            // cac tu co o trong file nao file day chua bao nhieu tu do
            if (!st.contains(word)) {
                ST<File,Integer> map = new ST<File,Integer>();
                map.put(file,1);
                st.put(word, map);
            }else {
                ST<File,Integer> map = st.get(word);
                if(map.contains(file)){
                    map.put(file,map.get(file)+1);
                }else {
                    map.put(file,1);   
                }
                st.put(word,map);
            }
        }
        
        //wordInFile.put(file,wordPerFile);
    }
    
    public void query(String word){
        ST<File,Integer> map = st.get(word);
        System.out.println("Danh sach cac file chua tu "+word);
        for(File file : map.keys()){
            Integer frequencyWord = map.get(file);
            System.out.println(file.getName() + "\t" +frequencyWord);
        }
    }
    
    public ST<String, ST<File,Integer>> getST(){
        return st;
    }
    
    // public ST<File, ST<String,Integer>> getWordInFile(){
        // return wordInFile;
    // }

    /*
    public static void main(String[] args) {
        // input "ex1.txt","ex2.txt","ex3.txt","ex4.txt"
        FileFrequencyIndex ffi = new FileFrequencyIndex();
        for (String filename : args) {
            File file = new File(filename);
            ffi.addFile(file);
        }
        ffi.query("it");
        
    }
    */

}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

