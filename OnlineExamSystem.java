import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class OnlineExamSystem extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel loginPanel;
    private JPanel examPanel;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton startExamButton;
    private JButton logoutButton;
    private JButton nextButton;
    private JButton finishButton;
    private JTextArea questionText;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup optionsGroup;
    private JLabel statusLabel;

    private Map<String, String> registeredUsers;

    private String loggedInUser;

    public OnlineExamSystem() {
        setTitle("Online Examination System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        loginPanel = createLoginPanel();
        examPanel = createExamPanel();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(examPanel, "Exam");

        add(cardPanel);

        registeredUsers = new HashMap<>();

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        statusLabel = new JLabel("");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Username:"), constraints);
        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Password:"), constraints);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(registerButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(statusLabel, constraints);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        return panel;
    }

    private JPanel createExamPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionText = new JTextArea("Q1: What is the capital of France?");
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setEditable(false);
        questionPanel.add(questionText, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        option1 = new JRadioButton("Option A: Paris");
        option2 = new JRadioButton("Option B: Berlin");
        option3 = new JRadioButton("Option C: London");
        option4 = new JRadioButton("Option D: Madrid");
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        questionPanel.add(optionsPanel, BorderLayout.SOUTH);
        panel.add(questionPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        nextButton = new JButton("Next");
        finishButton = new JButton("Finish");
        buttonPanel.add(nextButton);
        buttonPanel.add(finishButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(this);
        finishButton.addActionListener(this);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (registeredUsers.containsKey(username)) {
                if (registeredUsers.get(username).equals(password)) {
                    loggedInUser = username;
                    statusLabel.setText("Logged in as: " + loggedInUser);
                    cardLayout.show(cardPanel, "Exam"); // Switch to exam panel
                } else {
                    statusLabel.setText("Invalid password");
                }
            } else {
                statusLabel.setText("User not registered");
            }
        } else if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!registeredUsers.containsKey(username)) {
                registeredUsers.put(username, password);
                statusLabel.setText("Registered successfully!");
            } else {
                statusLabel.setText("Username already registered");
            }
        } else if (e.getSource() == nextButton) {
            // Process the selected answer, move to the next question, etc.
            // For this example, let's just update the question text.
            questionText.setText("Q2: Which planet is known as the 'Red Planet'?");
            option1.setText("Option A: Venus");
            option2.setText("Option B: Mars");
            option3.setText("Option C: Jupiter");
            option4.setText("Option D: Saturn");
            optionsGroup.clearSelection();
        } else if (e.getSource() == finishButton) {
            int score = calculateScore();
            String resultMessage = "Your Score: " + score + "/2";
            JOptionPane.showMessageDialog(this, resultMessage, "Exam Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Handle other actions (logout)
            if (e.getSource() == logoutButton) {
                cardLayout.show(cardPanel, "Login"); // Switch to login panel
                loggedInUser = null;
                statusLabel.setText("Logged out");
                clearFields();
            }
        }
    }

    private int calculateScore() {
        int score = 0;
        if (option1.isSelected()) {
            score++;
        }
        // Add more scoring logic for other questions

        return score;
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText(""); // Clear the password field
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnlineExamSystem examSystem = new OnlineExamSystem();
            examSystem.setVisible(true);
        });
    }
}
