import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;
    private JLabel roundsLabel;
    private JLabel scoreLabel;
    private int numberToGuess;
    private int maxAttempts = 10;
    private int rounds = 1;
    private int score = 0;
    private int attempts = 0; 

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        generateNewNumberToGuess();

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        roundsLabel = new JLabel("Rounds: " + rounds);
        scoreLabel = new JLabel("Score: " + score);

        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultArea);
        add(roundsLabel);
        add(scoreLabel);

        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        int playerGuess = Integer.parseInt(guessField.getText());

        if (playerGuess < numberToGuess) {
            resultArea.append("Attempt #" + (maxAttempts - attempts + 1) + ": Your guess is too low.\n");
        } else if (playerGuess > numberToGuess) {
            resultArea.append("Attempt #" + (maxAttempts - attempts + 1) + ": Your guess is too high.\n");
        } else {
            resultArea.append("Congratulations! You've guessed the number " + numberToGuess + " in " + (maxAttempts - attempts + 1) + " attempts!\n");
            score++;
            scoreLabel.setText("Score: " + score);
            generateNewNumberToGuess();
        }

        attempts++;

        if (attempts > maxAttempts) {
            resultArea.append("Round " + rounds + " is over. Your score: " + score + "\n\n");
            rounds++;
            roundsLabel.setText("Rounds: " + rounds);
            generateNewNumberToGuess();
            attempts = 1;
        }

        guessField.setText("");
    }

    private void generateNewNumberToGuess() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}

