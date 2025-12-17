public abstract class Player {
    public int x;
    public int y;
    public int width;
    public int height;

    public double velocityY;
    public final double gravity = 0.45;

    public Player(int startX, int startY, int w, int h, double velocityY) {
        this.x = startX;
        this.y = startY;
        this.width = w;
        this.height = h;
        this.velocityY = velocityY;
    }

    public void jump() {}

    public void update() {}
        
}
