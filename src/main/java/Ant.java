import java.util.*;

class Ant extends ObjectSquare {
    protected int move;
    public final String ICON_WITHOUT_LEAF = "icons/Ant.png";
    public final String ICON_WITH_LEAF = "icons/AntWithLeaf.png";
    protected boolean holdsLeaf;


    public void changeHoldingLeaf() {
        holdsLeaf = !holdsLeaf;
    }

    public int getMove() {
        return move;
    }


    Ant() {
        staticName = "ant";
        holdsLeaf = false;
        ICON = ICON_WITHOUT_LEAF;
        x = Max.SIZE / 2;
        y = Max.SIZE / 2;
        preX=x;
        preY=y;
    }

    Ant(int X, int Y) {
        staticName = "ant";
        holdsLeaf = false;
        ICON = ICON_WITHOUT_LEAF;
        x = X;
        y = Y;
        preX=x;
        preY=y;
    }


    public void move(ObjectSquare[][] objects) {
        preX=x;
        preY=y;
        int movX = x;
        int movY = y;
        System.out.println("przed=x:"+movX+"y:"+movY);
        Random ranGen = new Random();
        boolean antMoved = false;
        while (!antMoved) {
            boolean goesStraight = ranGen.nextInt(2) == 0;
            if (goesStraight) {
                switch ((int) (degreesFacing)) {
                    case (int) UP_DEGREE:
                        movX = moveIfNotUnder(movX);
                        antMoved = true;
                        break;
                    case (int) LEFT_DEGREE:
                        movY = moveIfNotUnder(movY);
                        antMoved = true;
                        break;
                    case (int) DOWN_DEGREE:
                        movX = checkIfNotAbove(movX);
                        antMoved = true;
                        break;
                    case (int) RIGHT_DEGREE:
                        movY = checkIfNotAbove(movY);
                        antMoved = true;
                        break;
                }
            } else {
                int number = ranGen.nextInt(4);
                switch (number) {
                    case 0:
                        if (!checkIfTurnsBack(UP_DEGREE)) {
                            degreesFacing = UP_DEGREE;
                            movX = moveIfNotUnder(movX);
                            antMoved = true;
                        }
                        break;
                    case 1:
                        if (!checkIfTurnsBack(LEFT_DEGREE)) {
                            degreesFacing = LEFT_DEGREE;
                            movY = moveIfNotUnder(movY);
                            antMoved = true;
                        }
                        break;
                    case 2:
                        if (!checkIfTurnsBack(DOWN_DEGREE)) {
                            degreesFacing = DOWN_DEGREE;
                            movX = checkIfNotAbove(movX);
                            antMoved = true;
                        }
                        break;
                    case 3:
                        if (!checkIfTurnsBack(RIGHT_DEGREE)) {
                            degreesFacing = RIGHT_DEGREE;
                            movY = checkIfNotAbove(movY);
                            antMoved = true;
                        }
                        break;
                }
            }
        }
        System.out.println("Po=x:"+movX+"y:"+movY);

        if (checkIfCanGoThere(movX, movY,objects)) {
           /* if (checkHereIsLeaf(movX, movY)) {
                if (!this.holdsLeaf) {
                    this.changeHoldingLeaf();
                    x = movX;
                    y = movY;
                }
            } else {
             */   x = movX;
                y = movY;
            //}
        }

        move++;
    }

    private boolean checkHereIsLeaf(int movX, int movY, ObjectSquare[][] objects) {

        return true;
        //else
        //    return false;
    }

    private boolean checkIfTurnsBack(double direction) {

        if ((360 - this.degreesFacing) % 360 != direction)
            return false;
        else
            return true;

    }

    private int moveIfNotUnder(int num) {
        if (num == 0) {
            degreesFacing = (360 - degreesFacing) % 360;
            return num;

        } else {

            return num - 1;
        }
    }

    private int checkIfNotAbove(int num) {
        if (num == (Max.SIZE - 1)) {
            degreesFacing = (360 - degreesFacing) % 360;
            return num;
        } else
            return num + 1;
    }

    private boolean checkIfCanGoThere(int x, int y, ObjectSquare[][] objects) {
        if (objects[x][y].getName().equals(Ant.staticName)
                || objects[x][y].getName().equals(Queen.staticName)) {
            System.out.println("false"+objects[x][y].getName() + "VS "+ Ant.staticName);
            return false;
        } else
            System.out.println("true"+objects[x][y].getName() + "VS "+ Ant.staticName);
            return true;
    }


}
