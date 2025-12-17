import java.awt.CardLayout;
import javax.swing.JPanel;

public class AppPanel extends JPanel {

    private CardLayout cards = new CardLayout();

    private MainPanel authPanel;   // login + signup
    private GamePanel gamePanel;   // flappy bird

    private String currentUser;
    private int highScore;


    public AppPanel() throws Exception {
        setLayout(cards);

        authPanel = new MainPanel(this);
        gamePanel = new GamePanel();

        add(authPanel, "AUTH");
        add(gamePanel, "GAME");

        cards.show(this, "AUTH");
    }

    public void showGame(String user, int highScore) {
        this.currentUser = user;
        this.highScore = highScore;

        gamePanel.setUser(user, highScore);
        cards.show(this, "GAME");
        gamePanel.requestFocusInWindow();
    }

}
