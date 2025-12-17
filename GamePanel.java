import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;


public class GamePanel extends JPanel implements ActionListener, KeyListener{



    private Fish1 fish1 = new Fish1(100, 250, 30, 30, 0);
    
    private ArrayList<PipePair> pipes = new ArrayList<>();

    // Base pipe settings
    private int pipeWidth = 70;
    private int pipeGap = 250;
    private int pipeSpawnDistance = 250;

    private int gameScore = 0;
    private boolean gameOver = false;
    private long startTime = System.currentTimeMillis();

    // Difficulty parameters
    private int pipeSpeed = 3;
    private double difficultyMultiplier = 0.0005; // increase per frame

    // Pipe spawning control
    private int nextSpawnX = 800;

    Timer timer;

    //Images
    private Image backgroundImage;
    private Image birdImage;
    private Image pipeImageTop;
    private Image pipeImageBottom;

    //Sounds
    private Sound jumpSound;
    private Sound scoreSound;
    private Sound hitSound;
    private Sound backgroundSong;

    private String currentUser;
    private int highScore;
    private int score;
   

    public GamePanel() {

        setFocusable(true);
        addKeyListener(this);
    
        // Start timer
        timer = new Timer(15, this);
    
        // Wait until panel is visible before starting game
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                startGame();    // <-- NEW
            }
        });

        // Load images
        ImageIcon icon = new ImageIcon("img/gameback.png");
        backgroundImage = icon.getImage();

        ImageIcon birdIcon = new ImageIcon("img/fish.png");
        birdImage = birdIcon.getImage();

        ImageIcon pipeTopIcon = new ImageIcon("img/pipeTop.png");
        pipeImageTop = pipeTopIcon.getImage();

        ImageIcon pipeBottomIcon = new ImageIcon("img/pipeBottom.png");
        pipeImageBottom = pipeBottomIcon.getImage();

        // Load sounds
        jumpSound = new Sound("/sounds/jump.wav");
        scoreSound = new Sound("/sounds/score.wav");
        hitSound = new Sound("/sounds/hit.wav");
        backgroundSong = new Sound("/sounds/backgroundSong.wav");
    }

    public void setUser(String user, int highScore) {
        this.currentUser = user;
        this.highScore = highScore;
    }


    public void startGame() {
        pipes.clear();
        gameScore = 0;
        gameOver = false;
        nextSpawnX = 800;
        startTime = System.currentTimeMillis();
    
        // initial pipes NOW safe because getHeight() is valid
        for (int i = 0; i < 4; i++) {
            spawnPipe(true);
        }
    
        timer.start();
        requestFocusInWindow(); // <-- gets keyboard focus
        backgroundSong.loop();
    }
    

    private void spawnPipe(boolean initial) {
        int height = (int) (Math.random() * 200) + 100;
        int x;
        if(initial){
            x = nextSpawnX;
        }else{
            x = 800;
        }

        pipes.add(new PipePair(x, height, pipeWidth, pipeGap, getHeight()));

        nextSpawnX += pipeSpawnDistance;
    }

    private void updateDifficulty() {
        long elapsed = (System.currentTimeMillis() - startTime);

        // Increase speed slowly
        pipeSpeed = 3 + (int)(elapsed * difficultyMultiplier);

        // Shrink gap slightly over time, but not too small
        pipeGap = Math.max(100, 250 - (int)(elapsed * 0.0002));

        // Reduce spacing a little, but keep playable
        pipeSpawnDistance = Math.max(130, 250 - (int)(elapsed * 0.0005));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {

            updateDifficulty();

            fish1.update();

            // Spawn new pipes if needed
            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).topPipe.x < 800 - pipeSpawnDistance)
                spawnPipe(false);

            // Move pipes, score, delete old ones
            for (int i = 0; i < pipes.size(); i++) {
                PipePair p = pipes.get(i);

                p.move(pipeSpeed);

                // Scoring
                if (!p.passed && p.topPipe.x + pipeWidth < fish1.x) {
                    gameScore++;
                    scoreSound.play();
                    p.passed = true;
                }

                // Remove old pipes
                if (p.topPipe.x + pipeWidth < -50) {
                    pipes.remove(i);
                    i--;
                }
            }

            // Collision detection
            Rectangle birdRect = new Rectangle(fish1.x, fish1.y, fish1.width, fish1.height);
            for (PipePair p : pipes) {
                if (p.topPipe.intersects(birdRect) || p.bottomPipe.intersects(birdRect)) {
                    backgroundSong.stop();
                    hitSound.play();
                    gameOver = true;
                    if (gameOver) {
                        if (gameScore > highScore) {
                            highScore = gameScore;
                            saveHighScore();
                        }
                    }
                    timer.stop();
                }
            }

            // Ground/ceiling
            if (fish1.y < 0 || fish1.y + fish1.height > getHeight()) {
                backgroundSong.stop();
                hitSound.play();
                gameOver = true;
                if (gameOver) {
                        if (gameScore > highScore) {
                            highScore = gameScore;
                            saveHighScore();
                        }
                    }
                timer.stop();
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        // Background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Fish
        g.drawImage(birdImage, fish1.x, fish1.y, fish1.width, fish1.height, null);

        // Pipes
        g.setColor(new Color(248, 131, 121));
        for (PipePair p : pipes) {
            g.fillRect(p.topPipe.x, p.topPipe.y, p.topPipe.width, p.topPipe.height);
            g.fillRect(p.bottomPipe.x, p.bottomPipe.y, p.bottomPipe.width, p.bottomPipe.height);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + gameScore, 10, 30);

        // Stopwatch
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        String timeStr = "Time: " + elapsed + "s";
        int w = g.getFontMetrics().stringWidth(timeStr);
        g.drawString(timeStr, getWidth() - w - 10, 30);

        // Game Over
        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 275, 300);

            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Press SPACE to restart", 265, 350);

            g.drawString("Score: " + gameScore, 335, 450);
            g.drawString("Time played: " + elapsed + "s", 295, 485);
        }
    }

    private void restart() {
        fish1 = new Fish1(100, 250, 30, 30, 0);
        startGame();  // restart entire game logic
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            restart();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fish1.jump();
            jumpSound.play();
        }
    }

    private void saveHighScore() {
        File file = new File("Content.txt");
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equals(currentUser)) {
                    line = parts[0] + "," + parts[1] + "," + highScore;
                }

                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(file, false)) {
            for (String l : lines) {
                writer.write(l + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



     @Override public void keyReleased(KeyEvent e) {}
     @Override public void keyTyped(KeyEvent e) {}
    
    }
