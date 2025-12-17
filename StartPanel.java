import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(GameFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Flappy Bird", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));

        JButton play = new JButton("Play");
        play.setFont(new Font("Arial", Font.BOLD, 28));
        play.addActionListener(e -> frame.showScreen(GameFrame.GAME));

        add(title, BorderLayout.CENTER);
        add(play, BorderLayout.SOUTH);
    }
    

}
