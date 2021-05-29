package assignments.assignment4a;

import javax.swing.*;
import java.awt.*;

class AppPanel extends JPanel {
    private JTextField infixExpressionTextField;
    private JLabel postfixExpressionLabel;
    private JLabel resultLabel;
    private JLabel errorMessagesLabel;

    public AppPanel(){
        super(new GridLayout(4, 2));
        setPreferredSize(new Dimension(500, 100));
        setBackground(new Color(192, 192, 192));

        initComponents();
    }

    private void initComponents() {
        add(new JLabel("Enter infix expression:"));

        infixExpressionTextField = new JTextField();
        infixExpressionTextField.setBackground(Color.yellow);
        infixExpressionTextField.addActionListener(e ->{
            String expression = infixExpressionTextField.getText();
            Calculate.start(expression);
            postfixExpressionLabel.setText(Calculate.getPostfixExpression());
            resultLabel.setText(Calculate.getResult());
            errorMessagesLabel.setText(Calculate.getErrorMessages().toString());
        });
        add(infixExpressionTextField);

        add(new JLabel("Postfix expression:"));

        // postfixExpressionLabel, resultLabel, and errorMessagesLabel
        // must be saved to a variable so that the text can be changed
        // later

        postfixExpressionLabel = new JLabel();
        add(postfixExpressionLabel);

        add(new JLabel("Result:"));

        resultLabel = new JLabel();
        add(resultLabel);

        add(new JLabel("Error messages:"));

        errorMessagesLabel = new JLabel();
        add(errorMessagesLabel);
    }
}
