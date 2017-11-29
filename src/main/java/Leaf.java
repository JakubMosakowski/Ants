import java.util.Random;

class Leaf extends ObjectSquare{


    public void setRaised(boolean Raised) {
        this.raised = Raised;
    }

    private boolean raised;

    public Leaf() {
        className ="leaf";
        ICON = "icons/Leaf.png";
        Random rand = new Random();
        raised=false;
        for (int i = 0; i < Max.LEAVES; i++) {
            x = rand.nextInt(Max.SIZE );
            y = rand.nextInt(Max.SIZE);
        }
    }

}
