import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;

/**
 * Created by Sriram on 4/7/14.
 */
public class CodingQuestionTest extends junit.framework.TestCase
{

public void test1(){
    CodingQuestion cq = new CodingQuestion("Print hello world",
            "System.out.println(\"hello world\")",
            "print hello world",
            "none");
    assertEquals("Print hello world",cq.getText());
    cq.setText("none");
    assertEquals("none",cq.getText());


}
    public void test2(){
        CodingQuestion cq = new CodingQuestion("Print hello world",
                "System.out.println(\"hello world\")",
                "print hello world",
                "none");
        assertEquals("System.out.println(\"hello world\")",cq.getAnswer());
        cq.setAnswer("nothing");
        assertEquals("nothing",cq.getAnswer());

    }
    public void test3(){
        CodingQuestion cq = new CodingQuestion("Print hello world",
                "System.out.println(\"hello world\")",
                "print hello world",
                "none");
        assertEquals("print hello world",cq.getCode());
        cq.setCode("nothing");
        assertEquals("nothing",cq.getCode());
    }

    public void test4(){
        CodingQuestion cq = new CodingQuestion("Print hello world",
                "System.out.println(\"hello world\")",
                "print hello world",
                "none");
        assertEquals("none",cq.getInput());
        cq.setInput("nothing");
        assertEquals("nothing",cq.getInput());
    }
    //This test shows that there is an implementation error in the wrapCodeInFile method
    public void test5(){
        CodingQuestion cq = new CodingQuestion("Print hello world",
                "System.out.println(\"hello world\")",
                "print hello world",
                "none");
        try{
        cq.wrapCodeInFile("code","filename");
            BufferedReader file = new BufferedReader(new FileReader("filename.java"));
           assertEquals("public class filename{",file.readLine());

        }catch(IOException e){

        }
    }
    //Inputing the answer to check() should return true, but returns false
    public void test6(){
        CodingQuestion cq = new CodingQuestion("Print hello world",
                "System.out.println(\"hello world\")",
                "print hello world",
                "none");
        assertEquals(false, cq.check("hello"));
        assertEquals(true, cq.check(cq.getAnswer()));
       // cq.check("hello");
    }

}
