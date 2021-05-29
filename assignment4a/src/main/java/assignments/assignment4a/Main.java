package assignments.assignment4a;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new AppFrame();
    }
}

class AppFrame extends JFrame{

    public AppFrame(){
        super("Infix -> Postfix Evaluator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new AppPanel());
        pack();
        setLocationRelativeTo(null);    // put window in the middle of the screen

        setVisible(true);

    }

}
