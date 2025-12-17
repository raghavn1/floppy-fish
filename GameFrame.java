import javax.swing.*;


import java.awt.*;

public class GameFrame extends JFrame {

    private CardLayout layout;
    private JPanel mainPanel;

    public static final String GAME = "game";

    public GameFrame() throws Exception{
        setTitle("Floppy Fish");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setContentPane(new AppPanel());



        setVisible(true);
        setLocationRelativeTo(null);
        
    }

    public void showScreen(String name) {
        layout.show(mainPanel, name);
    }

    public static void main(String[] args) throws Exception {
        new GameFrame();
    }
}