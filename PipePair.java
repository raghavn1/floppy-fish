import java.awt.*;

public class PipePair {

    public Rectangle topPipe;
    public Rectangle bottomPipe;
    public boolean passed = false;  // for scoring only once

    public PipePair(int x, int topHeight, int width, int gap, int panelHeight) {
        topPipe = new Rectangle(x, 0, width, topHeight);
        bottomPipe = new Rectangle(
            x,
            topHeight + gap,
            width,
            panelHeight - (topHeight + gap)
        );
    }

    public void move(int speed) {
        topPipe.x -= speed;
        bottomPipe.x -= speed;
    }
    
}
