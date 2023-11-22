import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int numberToGuess;
    private int maxAttempts;
    private int attemptsLeft;
    private JTextField textField;
    private JLabel resultLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textField = new JTextField();
        resultLabel = new JLabel();

        setLayout(new GridLayout(4, 1));

        JButton guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(new JLabel("Enter your guess:"));
        add(textField);
        add(guessButton);
        add(resultLabel);

        initializeGame();  // Move initializeGame() here
    }

    private void initializeGame() {
        Random random = new Random();
        int difficulty = chooseDifficulty();
        numberToGuess = random.nextInt((int) Math.pow(10, difficulty)) + 1;
        maxAttempts = 5 + difficulty * 2; // Adjust the maximum attempts based on difficulty
        attemptsLeft = maxAttempts;
        resultLabel.setText("Attempts left: " + attemptsLeft);
    }

    private int chooseDifficulty() {
        String[] options = {"Easy(1-10)", "Medium(1-100)", "Hard(1-1000)"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Choose difficulty level:",
                "Difficulty",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Return difficulty level: 1 for easy, 2 for medium, 3 for hard
        return choice + 1;
    }

    private void checkGuess() {
        String input = textField.getText().trim();

        if (input.isEmpty() || !input.matches("\\d+")) {
            resultLabel.setText("Invalid input. Please enter a number.");
            return;
        }

        int userGuess = Integer.parseInt(input);

        if (userGuess < numberToGuess) {
            resultLabel.setText("Too low! Try again.");
        } else if (userGuess > numberToGuess) {
            resultLabel.setText("Too high! Try again.");
        } else {
            resultLabel.setText("Congratulations! You guessed the number!");
            initializeGame(); // Start a new game
            return; // Exit the method to avoid decrementing attempts again
        }

        attemptsLeft--;
        resultLabel.setText(resultLabel.getText() + "  Attempts left: " + attemptsLeft);

        if (attemptsLeft == 0) {
            resultLabel.setText("Sorry, you ran out of attempts. The correct number was: " + numberToGuess);
            initializeGame(); // Start a new game
        }

        textField.setText(""); // Clear the text field after each guess
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}



