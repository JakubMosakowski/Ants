import java.io.*;
import java.util.*;

class Anthill {

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;
    private static  char TYPE='H';


    Anthill() {
        for (int i = 0; i < Max.MAX_ANTS; i++) {
            ants[i] = new Ant();
        }
        //TODO spawn queen at the middle
        spawnQueen();
        spawnLeaves();
    }



    private void spawnLeaves() {
        //TODO
    }

    private void spawnQueen() {
        queen = new Queen("Queen Elizabeth");
    }

    public void moveAnts() {
        //TODO
        for(int i=0;i<Max.MAX_ANTS;i++){
            ants[i].move();
            Board.boardSquaresTags[ants[i].x][ants[i].y]=Ant.TYPE;
        }

    }
}
