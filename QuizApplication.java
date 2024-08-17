import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    // Class to represent a question with options and the correct answer
    static class Question {
        String question;
        String[] options;
        String correctAnswer;

        Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    // List of quiz questions
    static List<Question> quizQuestions = new ArrayList<>();
    static int score = 0;
    static int totalQuestions = 0;
    static int correctAnswers = 0;
    static int incorrectAnswers = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define quiz questions
        quizQuestions.add(new Question(
            "What is the capital of France?",
            new String[]{"1. Berlin", "2. London", "3. Paris", "4. Madrid"},
            "3"
        ));
        quizQuestions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"},
            "2"
        ));
        quizQuestions.add(new Question(
            "Who wrote 'Romeo and Juliet'?",
            new String[]{"1. Mark Twain", "2. Charles Dickens", "3. William Shakespeare", "4. Jane Austen"},
            "3"
        ));

        totalQuestions = quizQuestions.size();

        for (int i = 0; i < quizQuestions.size(); i++) {
            askQuestion(quizQuestions.get(i), scanner);
        }

        // Display the final score and summary
        displayResults();

        scanner.close();
    }

    public static void askQuestion(Question question, Scanner scanner) {
        final boolean[] answered = {false};
        final boolean[] timeout = {false};

        // Timer setup for each question
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered[0]) {
                    System.out.println("\nTime's up!");
                    timeout[0] = true;
                    timer.cancel();
                }
            }
        }, 10000); // 10 seconds for each question

        // Display the question and options
        System.out.println("\n" + question.question);
        for (String option : question.options) {
            System.out.println(option);
        }

        // Allow user to answer
        while (!answered[0] && !timeout[0]) {
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            if (timeout[0]) break;  // Exit if time's up
            if (answer.equals(question.correctAnswer)) {
                System.out.println("Correct!");
                score++;
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer was option " + question.correctAnswer + ".");
                incorrectAnswers++;
            }
            answered[0] = true;
            timer.cancel(); // Cancel the timer if answered
        }
    }

    public static void displayResults() {
        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + "/" + totalQuestions);
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + incorrectAnswers);
    }
}
