import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class UserTest {
  
  //constructor tests
  @Test
  public void constructor1() {
    User x = new User("name","role", "username", "password");
    User y = x;
    assertEquals(x, y);
  }
  
  @Test
  public void constructor2() {
    User x = new User("name","role", "username", "password");
    assertEquals(x.getName(), "name");
  }
  
  //Generic User
  User x = new User("name","role", "username", "password");
  
  //id tests
  @Test
  public void getId1() {
    x.setId("100");
    assertEquals(x.getId(), "100");
  }
  
  @Test
  public void setId1() {
    x.setId("newID");
    assertEquals(x.getId(), "newID");
  }
  
  //name tests
  @Test
  public void getName1() {
    assertEquals(x.getName(), "name");
  }
  
  @Test
  public void setName1() {
    x.setName("S:LDKFJ:");
    x.setName("S:LDKFJ:WF12981921982");
    x.setName("S:LDKFJ:LkFJWEFOIJEWF12981921982");
    assertEquals(x.getName(), "S:LDKFJ:LkFJWEFOIJEWF12981921982");
  }
  
  //getQuizzes() tests
  @Test
  public void getQuizzes1() {
    x.setQuizzes(null);
    assertEquals(x.getQuizzes(), null);
  }
  
  @Test
  public void setQuizzes1() {
    Quiz y = new Quiz("kwiz");
    ArrayList<Quiz> list = new ArrayList<Quiz>();
    list.add(y);
    x.setQuizzes(list);
    assertEquals(list, x.getQuizzes());
  }
  
  //username tests
  @Test
  public void getUsername1() {
    assertEquals(x.getUsername(), "username");
  }
  
  @Test
  public void setUsername1() {
    x.setUsername("Oblong Tigers");
    assertEquals(x.getUsername(), "Oblong Tigers");
  }
  
  //password tests
  @Test
  public void getPassword1() {
    assertEquals(x.getPassword(), "password");
  }
  
  @Test
  public void setPassword1() {
    x.setPassword("2sm  art__4u");
    assertEquals("2sm  art__4u", x.getPassword());
  }
  
  //role tests
  @Test
  public void getRole1() {
    String theRole = x.getRole();
    assertEquals(theRole, x.getRole());
  }
  
  @Test
  public void setRole1() {
    x.setRole("got no role");
    assertEquals(x.getRole(), "got no role");
  }
  
  //email tests
  @Test
  public void getEmail1() {
    String theEmail = x.getEmail();
    assertEquals(theEmail, x.getEmail());
  }
  
  @Test
  public void setEmail1() {
    x.setEmail("got no Email");
    assertEquals(x.getEmail(), "got no Email");
  }
  
  //answerQuestion tests
  @Test
  public void answerQuestion1() {
    String quizId = "QuizId";
    String questionId = "QuestionId";
    ArrayList<Quiz> list = new ArrayList<Quiz>();
    Quiz y = new Quiz("kwiz");
    list.add(y);
    Quiz q = new Quiz(quizId);
    Quiz quiz = list.get(list.indexOf(q));
    Question b = quiz.answerQuestion(questionId);
    assertEquals(b, quiz.answerQuestion(questionId));
  }
}