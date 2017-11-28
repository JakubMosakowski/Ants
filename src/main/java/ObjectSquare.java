public class ObjectSquare {

    protected int x=0;
    protected int y=0;

    public int getPreX() {
        return preX;
    }

    public int getPreY() {
        return preY;
    }

    protected int preX=0;
    protected int preY=0;
    static public String staticName ="Empty object";
    protected String name=staticName;

    public String getName() {
        return name;
    }
    ObjectSquare(int X,int Y){
     x=X;
     y=Y;
    }
    ObjectSquare(){}

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
