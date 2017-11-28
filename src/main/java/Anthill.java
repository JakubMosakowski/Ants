import java.io.*;
import java.util.*;

class Anthill {

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;

    public int getId() {
        return id;
    }

    private int id = 0;
    private ObjectSquare[][] objects = new ObjectSquare[Max.SIZE][Max.SIZE];
    private ObjectSquare objectEmpty= new ObjectSquare();

    Anthill() {
        fillObjects();
        spawnQueen();
    }

    private void fillObjects() {
        for (int i = 0; i < Max.SIZE; i++)
            for (int j = 0; j < Max.SIZE; j++)
                objects[i][j] = new ObjectSquare();
    }

    public void spawnAnt() {
        ants[id] = new Ant(queen.getX(),queen.getY()+1);
        passToObjects(ants[id]);
        id++;
    }

    public void passToObjects(ObjectSquare object) {
        objects[object.getX()][object.getY()] = object;
    }

    public ObjectSquare[][] getObjects() {
        return objects;
    }

    public void spawnLeaves() {
        for (int i = 0; i < Max.LEAVES; i++) {
            leaves[i] = new Leaf();
        }
    }

    public void spawnQueen() {

        queen = new Queen("Queen Elizabeth");
        passToObjects(queen);

    }


    public void moveAnts() {

        for (int i = 0; i < Max.MAX_ANTS; i++) {
            if(ants[i]!=null){
                ants[i].move(objects);
                passToObjects(ants[i]);
                passToObjects(new ObjectSquare(ants[i].getPreX(),ants[i].getPreY()));

            }





        }


    }

    /*private void updateLeaves() {
        //TODO leaves sprawdzają które znikneły, te co znikneły dostają boolean - enabled
        for(int i=0;i<Max.SIZE;i++)
            for(int j=0;j<Max.SIZE;j++)
                for(Leaf leaf:leaves)
                    if(Board.boardSquaresTags[i][j]==Leaf.TYPE){
                        leaf.setRaised(true);
                        //TODO liscie niech sie ruszają razem z mrówką która ją przejmie
                        //TODO to jest zle bo sie pokazują te co są nie podniesione
                    }
    }*/
}
