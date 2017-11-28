import java.io.*;
import java.util.*;

class Anthill {

    public Ant[] getAnts() {
        return ants;
    }

    public Leaf[] getLeaves() {
        return leaves;
    }

    public Queen getQueen() {
        return queen;
    }

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;

    public int getId() {
        return id;
    }

    private int id = 0;
    private ObjectSquare[] objects = new ObjectSquare[Max.FIELDS];

    public ObjectSquare[] getCurrentObj() {
        return currentObj;
    }

    private ObjectSquare[] currentObj;


    Anthill() {
        spawnQueen();
    }

    public void trimObjects(){
        int i=0;
        for(ObjectSquare ob:objects){
            if(ob!=null)
                i++;
            else
                break;
        }
        currentObj=new ObjectSquare[i];
        for(int j=0;j<i;j++)
            currentObj[j]=objects[j];
    }


    public void spawnAnt() {
        ants[id] = new Ant(queen.getX(),queen.getY()+1);
        passToObjects(ants[id]);
        id++;
    }

    public void passToObjects(ObjectSquare object) {
        for(int i=0;i<Max.FIELDS;i++)
            if(objects[i]==null){
                objects[i]=object;
                break;
            }
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
        trimObjects();

        for (int i = 0; i < currentObj.length; i++) {
           if(currentObj[i].getName().equals(Ant.staticName)){
               System.out.println("TEST");
               ((Ant)currentObj[i]).move(currentObj);
            }
            else
               System.out.println("Else"+currentObj[i].getName());


//TODO queen sie przekręca
            //TODO jedna mrówka nie zabiera swojego śladu

        }
        trimObjects();


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
