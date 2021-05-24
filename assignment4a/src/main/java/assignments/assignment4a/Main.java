package assignments.assignment4a;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new AppGUI();
    }
}

class AppGUI extends JFrame{

    public AppGUI(){
        super("Infix -> Postfix Evaluator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        add(new panel());
        pack();
        setLocationRelativeTo(null);    // put window in the middle of the screen

        setVisible(true);

    }

    private class panel extends JPanel{

        public panel(){
            super(new GridLayout(4, 2));
            setPreferredSize(new Dimension(500, 100));
            setBackground(new Color(192, 192, 192));

            add(new JLabel("Enter infix expression"));

            JTextField infixExpression = new JTextField();
            infixExpression.setBackground(Color.yellow);
            infixExpression.setText("Text here");;
            add(infixExpression);

            add(new JLabel("Postfix expression"));

            add(new JLabel("Test"));

            add(new JLabel("Result:"));

            add(new JLabel("testing"));

            add(new JLabel("Error messages:"));

            add(new JLabel("[]"));
        }
    }
}
