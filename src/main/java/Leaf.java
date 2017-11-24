import java.util.Random;

class Leaf {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;
    public static char TYPE = 'L';

    public void setRaised(boolean raised) {
        this.raised = raised;
    }

    private boolean raised;

    public Leaf() {
        Random rand = new Random();
        raised=false;
        for (int i = 0; i < Max.LEAVES; i++) {
            x = rand.nextInt(Max.SIZE );
            y = rand.nextInt(Max.SIZE);
        }

    }

}
