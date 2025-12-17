import java.awt.CardLayout;
import javax.swing.JPanel;
import java.io.FileNotFoundException;

public class SubPanel extends JPanel {

    private CardLayout card = new CardLayout();

    public SubPanel(MainPanel main) throws FileNotFoundException {
        setLayout(card);

        add(new SignupPage(), "Signup");
        add(new LoginPage(main), "Login");

        card.show(this, "Login");
    }

    public void loginshow() {
        card.show(this, "Login");
    }

    public void signupshow() {
        card.show(this, "Signup");
    }
}
