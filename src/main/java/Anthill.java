import java.io.*;
import java.util.*;

class Anthill {

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;

    public int getId() {
        return id;
    }

    private  int id = 0;
    private ObjectSquare[][] objects = new ObjectSquare[Max.SIZE][Max.SIZE];

    Anthill() {
        fillObjects();
        spawnQueen();
    }

private void fillObjects(){
        for(int i=0;i<Max.SIZE;i++)
            for(int j=0;j<Max.SIZE;j++)
                objects[i][j]=new ObjectSquare();
}
    public void spawnAnt() {
        ants[id] = new Ant();
        System.out.println("Mrowka nr"+id);
        passToObjects(ants[id]);
        id++;
    }

    public  void passToObjects(ObjectSquare   object) {
        System.out.println("Przed nameW:"+(object).name);
        objects[object.getX()][object.getY()] = object;
        System.out.println("Po nameW:"+objects[object.getX()][object.getY()].name);

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
        System.out.println("Przed name:"+queen.name);
        passToObjects(queen);

    }


    public void moveAnts() {

        for (int i = 0; i < Max.MAX_ANTS; i++) {
            //Board.boardSquaresTags[ants[i].x][ants[i].y] = Board.TYPE_EMPTY;
           /* if(ants[i]!=null){
                ants[i].move(objects);
            }
*/
            //Board.setAnts(ants);
            //updateLeaves();
            //Board.setLeaves(leaves);


           // Board.boardSquaresTags[ants[i].x][ants[i].y] = Ant.TYPE;
        }
        //Board.passObjects(objects);

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
