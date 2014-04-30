/**
 * @author Nijiati Muhetaer
 * @date  04/10/2014
 * Design and flow of the test
 * 1. Expected value
 * 2. Instantiate an object
 * 3. Get the actual value
 * 4. Compare to test the method
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class QuizTest 
{

	@Test
	public void testQuiz() 
	{
		String expected = "name";
		Quiz qz = new Quiz("name");
		String actual = qz.getName();
		assertEquals(expected, actual);
	}
	
	public void testAnswerQuestion()
	{
		Quiz qz = new Quiz("");
	    String expected = qz.answerQuestion("").toString(); 
		String actual = qz.getQuestion(0).toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testScore()
	{
		Quiz quiz = new Quiz("NameOne");
		int scoreExpec = quiz.score();
		Quiz qz = new Quiz("NameTwo");
		int scoreActual = qz.score();
		assertEquals(scoreExpec, scoreActual);
	}

	@Test
	public void testEqualsObject()
	{
		boolean expected = false;
		Quiz qz = new Quiz("name");
		boolean actual = qz.equals(null);
		assertEquals(expected, actual);
	}

	@Test
	public void testClone() 
	{
		Quiz qz = new Quiz("name");
		Object a = qz.clone();
		Object b = qz.clone();
		assertEquals(a, b);
	}
}
