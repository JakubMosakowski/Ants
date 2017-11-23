import java.io.*;
import java.util.*;

class Anthill {

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;
    public  static  char TYPE='H';


    Anthill() {
        for (int i = 0; i < Max.MAX_ANTS; i++) {
            ants[i] = new Ant();
        }
        //TODO spawn queen at the middle
        spawnQueen();

    }



    public void spawnLeaves() {
        for(int i=0;i<Max.LEAVES;i++){
            leaves[i]=new Leaf();
            Board.boardSquaresTags[leaves[i].getX()][leaves[i].getY()]=Leaf.TYPE;
        }
    }

    public void spawnQueen() {

        queen = new Queen("Queen Elizabeth");
        Board.boardSquaresTags[queen.x][queen.y]=Queen.TYPE;
        //spawnAnthill
    }

    public void moveAnts() {
        //TODO
        for(int i=0;i<Max.MAX_ANTS;i++){
            Board.boardSquaresTags[ants[i].x][ants[i].y]=Board.TYPE_EMPTY;
            ants[i].move();
            Board.setAnts(ants);
            Board.setQueen(queen);

            Board.boardSquaresTags[ants[i].x][ants[i].y]=Ant.TYPE;
        }

    }
}
