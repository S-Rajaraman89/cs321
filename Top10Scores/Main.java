import java.util.*;

public class Main {

	private static DatabaseSingleton database;
	private static User user;
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		/*
		 * this is used to start the Grade Server; normally the server would run
		 * on its own and would not be part of this application.
		 */
		CONNECT_TO_GRADE_SERVER();

		System.out.println("Welcome to the CS321 Quiz Game!");
		database = DatabaseSingleton.getInstance();
		database.loadDatabase("database.txt");

		System.out
				.println("Your teacher should have provided you with a username and password. Please enter your username:");

		String username = scan.nextLine();
		user = database.getUser(username);
		while (user == null && !username.equals("quit")) {
			System.out
					.println("Couldn't find that username. Please try again, or type quit to exit.");
			username = scan.nextLine();
			if (username.equals("quit"))
				return;
			user = database.getUser(username);
		}
		System.out.println("Please enter your password:");
		String password = scan.nextLine();

		// catch possible nullpointer
		try {

			while (!user.getPassword().equals(password.trim())
					&& password.equals("quit")) {
				System.out
						.println("That password doesn't match. Please try again, or type quit to exit");
				password = scan.nextLine();
				if (password.equals("quit"))
					return;
			}
		} catch (NullPointerException e) {
			throw new NullPointerException();
		}
		System.out.println("Welcome " + user.getName() + "!");
		String message = "Please enter a number for one of the following options:"
				+ "\n\t1. Take a quiz\n\t2. Create a question\n\t3. View ";
		if (user.getRole().equals("Teacher"))
			message += "or change ";
		message += "grades+\n\t4. View Top 10 Grades\n\t(type quit to exit)\n\nEnter option number here:";

		System.out.println(message);
		String choice = scan.nextLine();
		while (!choice.equals("quit")) {
			if (choice.equals("1"))
				takeQuiz();
			else if (choice.equals("2"))
				createQuestion();
			else if (choice.equals("3"))
				viewGrades();
			else if (choice.equals("4"))
				viewTopTen();
			else
				choice = "quit";
			System.out.println(message);
			choice = scan.nextLine();
		}
		DISCONNECT_FROM_GRADE_SERVER();
		System.out.println("Thank you for using the CS321 Quiz Game!");
		System.exit(0);
	}

	public static void takeQuiz() {
		ArrayList<Quiz> quizzes = DatabaseSingleton.getQuizzes();
		if (quizzes.size() == 0) {
			System.out
					.println("There are no quizzes yet. Remember you can create a quiz by choosing to create a question. Returning to main menu.");
			return;
		}
		System.out
				.println("Brave student! Please select one of the quizzes below to take (by number):");
		for (int i = 0; i < quizzes.size(); i++)
			System.out.println("\t" + (i + 1) + quizzes.get(i).getName());
		System.out.println("Enter quiz number:");
		Integer id = new Integer(scan.nextLine());

		// get blank quiz from DB; keep a copy of quiz with user
		Quiz quiz = quizzes.get(id.intValue() - 1).clone();
		Question question = quiz.getQuestion(0);
		while (question != null)
			question = quiz.answerQuestion(question.getId());
		System.out.println("Quiz ended. Your score was " + quiz.score()
				+ " out of " + quiz.getQuestions().size());
		Date date = new Date();
		GradeServer.record(date.toString() + "," + user.getId() + ","
				+ quiz.getId() + "," + quiz.score());
	}

	public static void createQuestion() {
		System.out.println("Please enter a question:");
		String question = scan.nextLine();
		String input = null;
		String output = null;
		String options = null;
		System.out
				.println("Please choose a type of question (enter the number):\n\t1. Short Answer\n\t2. Coding\n\t3. Multiple Choice\n\nEnter number:");
		String type = scan.nextLine();
		if (type.equals("2")) {
			System.out
					.println("Please enter the input (to be stored inside input.txt):");
			input = scan.nextLine();
			System.out
					.println("Please enter the output (to be printed to standard output):");
			output = scan.nextLine();
		} else if (type.equals("3")) {
			System.out
					.println("Please enter the options, on the same line, separated by commas:");
			options = scan.nextLine();
		}
		System.out
				.println("Please enter the correct answer (use integers for a multiple choice question):");
		String answer = scan.nextLine();
		ArrayList<Quiz> quizzes = DatabaseSingleton.getQuizzes();
		Quiz quiz = null;
		System.out
				.println("Please choose a quiz (by number) from the list of existing quizzes:");
		System.out.println("\t0. Create new quiz");
		for (int i = 0; i < quizzes.size(); i++)
			System.out.println("\t" + (i + 1) + quizzes.get(i).getName());
		String quizNum = scan.nextLine();
		if (quizNum.equals("0")) {
			System.out.println("Please enter a quiz name:");
			String quizName = scan.nextLine();
			quiz = new Quiz(quizName);
			quizzes.add(quiz);
		} else
			quiz = quizzes.get((new Integer(quizNum)).intValue() - 1);
		Question q = null;
		switch ((new Integer(type)).intValue()) {
		case 1:
			q = new Question(question, answer);
			break;
		case 2:
			q = new CodingQuestion(question, input, output, answer);
			break;
		case 3:
			String[] answers = options.split(",");
			ArrayList<String> o = new ArrayList<String>();
			for (int i = 0; i < answers.length; i++)
				o.add(answers[i]);
			q = new MultipleChoiceQuestion(question, answer, o);
			break;
		default: 
			System.out.println("Invalid response.");
		}
		if(q==null){
			throw new NullPointerException();
		}
		else
			quiz.addQuestion(q);
	}

	public static void viewGrades() {
		ArrayList<String> allGrades = new ArrayList<String>();
		ArrayList<User> users = DatabaseSingleton.getUsers();
		if (user.getRole().equals("Teacher")) {
			for (int i = 0; i < users.size(); i++) {
				ArrayList<String> result = GradeServer.lookup(users.get(i)
						.getId());
				for (int j = 0; j < result.size(); j++)
					allGrades.add(result.get(j));
			}
		} else
			allGrades = GradeServer.lookup(user.getId());
		for (int i = 0; i < allGrades.size(); i++) {
			String[] pieces = allGrades.get(i).split(",");
			User user = database.getUser(pieces[0].substring(0,
					pieces[0].indexOf(" ")));
			Quiz quiz = database.getQuiz(pieces[0].substring(
					pieces[0].indexOf(" ") + 1, pieces[0].length()));
			System.out.println((i + 1) + "Name: " + user.getName() + " Quiz: "
					+ quiz.getName() + " Score: " + pieces[1]);
		}

		if (user.getRole().equals("Teacher")) {
			System.out
					.println("If you would like to manually override a grade, enter the item number above, otherwise enter quit:");
			String choice = scan.nextLine();
			if (choice.equals("quit"))
				return;
			String[] pieces = allGrades.get(
					(new Integer(choice)).intValue() - 1).split(",");
			User user = database.getUser(pieces[0].substring(0,
					pieces[0].indexOf(" ")));
			System.out.println("Enter the new grade for " + user.getName()
					+ ":");
			String grade = scan.nextLine();
			GradeServer.record(pieces[0] + "," + grade + "\n");
			System.out.println("GradeServer updated. (flushed every minute)");
		}
	}

	public static void viewTopTen() {
		// Ask for quiz id
		System.out.println("What's the quiz id?");
		String id = scan.nextLine();
		if (id.equals("quit"))
			return;

		ArrayList<String> allGrades = new ArrayList<String>();
		ArrayList<User> users = DatabaseSingleton.getUsers();
		ArrayList<String> topten = new ArrayList<String>();

		for (User u : users) {
			// Find the quizzes with same ID
			allGrades = GradeServer.lookup(u.getId());
			// System.out.println("Search through this user "+u.getId());
			for (String a : allGrades) {
				// System.out.println("One of the grades "+a);
				if (a.contains(id) && topten.size() < 10) {
					// System.out.println("Adding this string "+a);
					topten.add(a);
				}
			}
		}
		// Sort the quizzes
		Collections.sort(topten, new Comparator<String>() {
			public int compare(String quiz1, String quiz2) {
				int grade1 = quiz1.lastIndexOf(",") + 1;
				int grade2 = quiz2.lastIndexOf(",") + 1;
				Integer gradeSubString1 = Integer.parseInt(quiz1
						.substring(grade1));
				Integer gradeSubString2 = Integer.parseInt(quiz2
						.substring(grade2));

				return gradeSubString2.compareTo(gradeSubString1);
			}
		});
		for (int x = 0; x < 10; x++) {
			if (x >= topten.size())
				break;
			System.out.println(x + 1 + ". " + topten.get(x));
		}
	}

	public static void CONNECT_TO_GRADE_SERVER() {
		// Ideally the Grade Server would be an external system, not a thread
		// running in this
		// application.
		(new Thread(new GradeServer())).start();
	}

	public static void DISCONNECT_FROM_GRADE_SERVER() {
		// We should ideally log some information here and formally disconnect
		// from the Grade Server,
		// but for our implementation, since it's just a local thread, we are
		// not doing anything
	}
}
