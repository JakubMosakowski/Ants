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

    public Leaf() {
        Random rand = new Random();

        for (int i = 0; i < Max.LEAVES; i++) {
            x = rand.nextInt(Max.SIZE );
            y = rand.nextInt(Max.SIZE);
        }

    }

}
