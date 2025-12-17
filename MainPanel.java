import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.io.FileNotFoundException;

public class MainPanel extends JPanel {

    private AppPanel app;
    private SubPanel sub;

    public MainPanel(AppPanel app) throws FileNotFoundException {
        this.app = app;
        setLayout(new BorderLayout());

        sub = new SubPanel(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(164, 125, 171));
        JButton signup = new JButton("SignUp");
        JButton login = new JButton("Login");

        new ControllerClass(sub, signup, login);

        buttonPanel.add(signup);
        buttonPanel.add(login);

        add(sub, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void loginSuccessful(String user, int highScore) {
        app.showGame(user, highScore);
    }
    
}
