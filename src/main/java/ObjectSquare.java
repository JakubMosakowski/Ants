public class ObjectSquare {

    protected int x = 0;
    protected int y = 0;
    protected int preX = 0;
    protected int preY = 0;
    protected String className;
    protected String name;
    protected boolean visible = true;
    protected int id;
    protected String ICON = "icons/Empty.png";
    static final double UP_DEGREE = 0;
    static final double DOWN_DEGREE = 180;
    static final double LEFT_DEGREE = 270;
    static final double RIGHT_DEGREE = 90;
    protected double degreesFacing;

    ObjectSquare(int X, int Y) {
        x = X;
        y = Y;
    }

    ObjectSquare() {
    }

    public int getId() {
        return id;
    }

    public int getPreX() {
        return preX;
    }

    public int getPreY() {
        return preY;
    }

    public String getClassName() {
        return className;
    }

    public String getICON() {
        return ICON;
    }

    public double getDegreesFacing() {
        return degreesFacing;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setId(int id) {
        this.id = id;
    }
}
