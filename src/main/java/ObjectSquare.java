public class ObjectSquare {

    protected int x=0;
    protected int y=0;
    protected  String name="Empty object";
    public String getName() {
        return name;
    }

    protected  String ICON= "icons/Empty.png";
    static final double UP_DEGREE = 0;
    static final double DOWN_DEGREE = 180;
    static final double LEFT_DEGREE = 270;
    static final double RIGHT_DEGREE = 90;
    double degreesFacing;

    public int getX() {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
