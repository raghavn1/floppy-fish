public class Fish1 extends Player {



    public Fish1(int startX, int startY, int w, int h, double velocityY) {
        super(startX, startY, w, h, velocityY);
    }

    @Override
    public void jump() {
        velocityY = -7;
    }

    @Override
    public void update() {
        velocityY += gravity;
        if (velocityY > 9.8) velocityY = 9.8;
        y += velocityY;
    }


    
    
}
