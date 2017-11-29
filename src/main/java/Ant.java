import java.util.*;

class Ant extends ObjectSquare {
    protected int move;
    public final String ICON_WITHOUT_LEAF = "icons/Ant.png";
    public final String ICON_WITH_LEAF = "icons/AntWithLeaf.png";
    protected boolean holdsLeaf;

    public int getQueenX() {
        return queenX;
    }

    public void setQueenX(int queenX) {
        this.queenX = queenX;
    }

    public int getQueenY() {
        return queenY;
    }

    public void setQueenY(int queenY) {
        this.queenY = queenY;
    }

    private int queenX;
    private int queenY;


    public void changeHoldingLeaf() {

        holdsLeaf = !holdsLeaf;
        if (holdsLeaf)
            ICON = ICON_WITH_LEAF;
        else
            ICON = ICON_WITHOUT_LEAF;
    }

    public int getMove() {
        return move;
    }


    Ant() {
        className = "ant";
        holdsLeaf = false;
        ICON = ICON_WITHOUT_LEAF;
        setXY();
        setPreXY();
    }

    private void setPreXY() {
        preX = x;
        preY = y;
    }

    private void setXY() {
        x = Max.SIZE / 2;
        y = Max.SIZE / 2;
    }

    Ant(int X, int Y) {
        className = "ant";
        holdsLeaf = false;
        ICON = ICON_WITHOUT_LEAF;
        x = X;
        y = Y;
        setPreXY();
    }


    public boolean isHoldingLeaf() {
        return holdsLeaf;
    }

    public void move(ObjectSquare[] objects) {
        setPreXY();
        int movX = x;
        int movY = y;

        Random ranGen = new Random();
        int number = ranGen.nextInt(4);
        boolean goesStraight = ranGen.nextInt(2) == 0;
        if (this.holdsLeaf) {
            number = getDirection();
            switch (number) {
                case 0: {
                    degreesFacing = UP_DEGREE;
                    movX = moveIfNotUnder(movX);
                }
                break;
                case 1: {
                    degreesFacing = LEFT_DEGREE;
                    movY = moveIfNotUnder(movY);
                }
                break;
                case 2: {
                    degreesFacing = DOWN_DEGREE;
                    movX = checkIfNotAbove(movX);
                }
                break;
                case 3: {
                    degreesFacing = RIGHT_DEGREE;
                    movY = checkIfNotAbove(movY);
                }
                break;
            }
        } else {
            if (goesStraight) {
                switch ((int) (degreesFacing)) {
                    case (int) UP_DEGREE:
                        movX = moveIfNotUnder(movX);
                        break;
                    case (int) LEFT_DEGREE:
                        movY = moveIfNotUnder(movY);
                        break;
                    case (int) DOWN_DEGREE:
                        movX = checkIfNotAbove(movX);
                        break;
                    case (int) RIGHT_DEGREE:
                        movY = checkIfNotAbove(movY);
                        break;
                }
            } else {
                switch (number) {
                    case 0:
                        if (!checkIfTurnsBack(UP_DEGREE)) {
                            degreesFacing = UP_DEGREE;
                            movX = moveIfNotUnder(movX);
                        }
                        break;
                    case 1:
                        if (!checkIfTurnsBack(LEFT_DEGREE)) {
                            degreesFacing = LEFT_DEGREE;
                            movY = moveIfNotUnder(movY);
                        }
                        break;
                    case 2:
                        if (!checkIfTurnsBack(DOWN_DEGREE)) {
                            degreesFacing = DOWN_DEGREE;
                            movX = checkIfNotAbove(movX);
                        }
                        break;
                    case 3:
                        if (!checkIfTurnsBack(RIGHT_DEGREE)) {
                            degreesFacing = RIGHT_DEGREE;
                            movY = checkIfNotAbove(movY);
                        }
                        break;
                }
            }
        }
        changeXY(objects, movX, movY);

        move++;
    }

    private int getDirection() {
        if (queenX > this.x)
            return 2;
        if (queenX < this.x)
            return 0;
        if (queenY > this.y)
            return 3;
        else
            return 1;
    }

    private void changeXY(ObjectSquare[] objects, int movX, int movY) {
        if (checkIfCanGoThere(movX, movY, objects)) {
            if (checkHereIsLeaf(movX, movY, objects)) {
                if (!this.holdsLeaf) {
                    this.changeHoldingLeaf();
                    x = movX;
                    y = movY;

                } else {
                    for (ObjectSquare ob : objects)
                        if (ob.getX() == movX && ob.getY() == movY)
                            if (ob.getClassName().equals(new Leaf().getClassName()))
                                    x = movX;
                                    y = movY;

                }
            } else {
                x = movX;
                y = movY;
            }
        }
    }

    private boolean checkHereIsLeaf(int movX, int movY, ObjectSquare[] objects) {
        for (ObjectSquare ob : objects)
            if (ob.getX() == movX && ob.getY() == movY)
                if (ob.getClassName().equals(new Leaf().getClassName())&&
                        ob.visible)
                    return true;
                else
                    break;
        return false;
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

    private boolean checkIfCanGoThere(int x, int y, ObjectSquare[] objects) {
        for (ObjectSquare object : objects) {
            if (object.getX() == x
                    && object.getY() == y) {
                if (object.getClassName().equals(this.className)
                        || object.getClassName().equals(new Queen().className)) {
                    return false;
                } else
                    break;
            }
        }

        return true;
    }


}
