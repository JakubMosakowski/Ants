import java.io.*;
import java.util.*;

class Anthill {

    protected Ant ants[] = new Ant[Max.MAX_ANTS];
    protected Leaf leaves[] = new Leaf[Max.LEAVES];
    protected Queen queen;
    public static char TYPE = 'H';


    Anthill() {
        for (int i = 0; i < Max.MAX_ANTS; i++) {
            ants[i] = new Ant();
        }
        spawnQueen();

    }


    public void spawnLeaves() {
        for (int i = 0; i < Max.LEAVES; i++) {
            leaves[i] = new Leaf();
            Board.boardSquaresTags[leaves[i].getX()][leaves[i].getY()] = Leaf.TYPE;
        }
    }

    public void spawnQueen() {

        queen = new Queen("Queen Elizabeth");
        Board.boardSquaresTags[queen.x][queen.y] = Queen.TYPE;
        spawnAnthill();
    }

    private void spawnAnthill() {
        int num;

        num = queen.getY();
        if (num + 1 < 15) {

            Board.boardSquaresTags[queen.getX()][num + 1] = Anthill.TYPE;
        } else if (num - 1 > 0) {
            Board.boardSquaresTags[queen.getX()][num - 1] = Anthill.TYPE;
        } else {
            num = queen.getX();
            if (num + 1 < 15) {
                Board.boardSquaresTags[num + 1][queen.getY()] = Anthill.TYPE;
            } else if (num - 1 > 0) {

                Board.boardSquaresTags[num - 1][queen.getY()] = Anthill.TYPE;
            }
        }

    }

    public void moveAnts() {

        for (int i = 0; i < Max.MAX_ANTS; i++) {
            Board.boardSquaresTags[ants[i].x][ants[i].y] = Board.TYPE_EMPTY;
            ants[i].move();
            Board.setAnts(ants);
            //updateLeaves();
            //Board.setLeaves(leaves);


            Board.boardSquaresTags[ants[i].x][ants[i].y] = Ant.TYPE;
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
