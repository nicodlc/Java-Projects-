package summer23.project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import cs2336su.MyLinkedList; //object is being imported, hence it is not shown on the p2final.java class

public class project2final{
    BufferedReader inputFileReader;

    //string arraylist containing all the unique keywords
    protected MyLinkedList<bookInfoNode> displayKeys = new MyLinkedList<>();
    public BST<nodeElement> keywordBST = new BST<>();
    //binary search tree containing all keywords, wihout repetition

    public static void main(String [] args) {
        project2final test = new project2final("summer23\\project2\\bookData.txt");
    }
    
    public project2final(String filename) {
        try { // in try/catch block to catch FileNotFoundException
            // from java source code of FileReader(String) 
            // Java Def: public FileReader(String fileName) throws FileNotFoundException

            inputFileReader = new BufferedReader(new FileReader(filename));
            if (inputFileReader == null) {
                System.out.println("Error: you must open the file first!");
            }
            else {
                while (readNextRecord());
                keywordBST.inorder();
            } 
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } 

    public boolean readNextRecord() {
        
        try {
            String data = inputFileReader.readLine();
            if (data == null) return false;
            //System.out.printf("Test %s\n", data);
            int titleId = Integer.parseInt(data);
            String title = inputFileReader.readLine();
            String author = inputFileReader.readLine();
            int numberOfKeys = Integer.parseInt(inputFileReader.readLine());
            bookInfoNode bookDataNode = new bookInfoNode(titleId, title, author);

            for(int i = 0; i < numberOfKeys; i++){
                String keyword = inputFileReader.readLine().trim();
                //storing all the different keywords 
                keywordBST.insert(keyword, bookDataNode);
                //keywordBST.insert(bookDataNode);
            } 

            inputFileReader.readLine();

            //System.out.println(a.toString() + a.printNodeList());
        }
        catch (IOException e) {
            System.out.printf("%s\n", e);
        }
        return true;
    }

}

class bookInfoNode { // "Aricles"

    int id;
    String title;
    String author;

    public bookInfoNode(int i, String t, String a) {
        id = i;
        title = t;
        author = a;
    }


    @Override
    public String toString() {
        return String.format(" %d | %s | %s", id, title, author);
    }

}

class nodeElement implements Comparable<nodeElement>{
    String k;
    bookInfoNode b;
    MyLinkedList<bookInfoNode> linkList;

    public nodeElement(String str, bookInfoNode bno) {
        k = str;
        b = bno;
        linkList = new MyLinkedList<>();
        linkList.add(bno);
    }

    public String getKey(){
        return k;
    }

    public MyLinkedList<bookInfoNode> getLinkeddList(){
        return linkList;
    }

    @Override
    public int compareTo(nodeElement o) {
        // TODO Auto-generated method stub
        return k.compareTo(o.toString());
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Keyword: ").append(k).append("\n");
        
        for (bookInfoNode book : linkList) {
            sb.append("- ").append(book.toString()).append("\n");
        }
        
        return sb.toString();
    }
}
