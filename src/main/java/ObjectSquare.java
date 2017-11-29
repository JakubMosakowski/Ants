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
    protected String className;
    protected String name;
    protected boolean visible=true;
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }
    ObjectSquare(int X,int Y){
     x=X;
     y=Y;
    }
    ObjectSquare(){}

    protected  String ICON= "icons/Empty.png";

    public String getICON() {
        return ICON;
    }

    static final double UP_DEGREE = 0;
    static final double DOWN_DEGREE = 180;
    static final double LEFT_DEGREE = 270;
    static final double RIGHT_DEGREE = 90;

    public double getDegreesFacing() {
        return degreesFacing;
    }

    protected double degreesFacing;

    public int getX() {
        return x;
    }

    public int getY()
    {
        return y;
    }

}
