
import org.junit.*;
import static org.junit.Assert.*;

/*
 *Created by Souraj Chadha
 *
*/
 
public class QuestionTest{

	public static void main(String args[]){
		
		//constructor
		public void test1(){
		Question q = new Question("text","answer");
		string t = "text";
		string a = "answer";
		int count = 1;
		assertEquals(q.getText(),t);
		assertEquals(q.getAnswer(),a);
		assertEquals(q.getId(),count);
		
		}
		
		//check method
		public void test2(){
		Question q = new Question("text","answer");
		assertEquals(q.check("answer"),true);
		}
		
		//toString()
		public void test3(){
		Question q = new Question("text","answer");
		String a = "Short Answer Question:\n\t"+"text"+"\n\n\tType your answer here:";
		assertEquals(a,q.toString());
		}
		
		//equals()
		public void test4(){
		Question q = new Question("text","answer");
		assertEquals();
		assertEquals();
		
		
		}
		
		
	}

}