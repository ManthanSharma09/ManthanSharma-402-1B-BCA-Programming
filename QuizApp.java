import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] questions = {
            "1. Which language is used to style web pages?\nA. HTML\nB. CSS\nC. Java\nD. Python",
            "2. Which keyword is used to create an object in Java?\nA. object\nB. new\nC. this\nD. class",
            "3. What does SQL stand for?\nA. Structured Question Language\nB. Strong Query Language\nC. Structured Query Language\nD. Simple Query Language",
            "4. Which data structure uses FIFO principle?\nA. Stack\nB. Queue\nC. Tree\nD. Graph",
            "5. What is the value of 25 in binary?\nA. 11001\nB. 10011\nC. 11010\nD. 11111"
        };

        char[] answers = {'B', 'B', 'C', 'B', 'A'};
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            char ans = Character.toUpperCase(sc.next().charAt(0));

            if (ans == answers[i]) {
                score++;
            }
        }

        System.out.println("\nFinal Score: " + score + " out of " + questions.length);
        sc.close();
    }
}
