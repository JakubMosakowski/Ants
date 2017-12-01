import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class Anthill {

    private Ant ants[] = new Ant[Max.MAX_ANTS_USER];
    private Leaf leaves[] = new Leaf[Max.LEAVES_USER];
    private Queen queen;
    private int id = 0;
    private ObjectSquare[] objects = new ObjectSquare[Max.FIELDS];
    private ObjectSquare[] currentObj;

    Anthill() {
        spawnQueen();
        spawnLeaves();
    }

    public Ant[] getAnts() {
        return ants;
    }

    public Leaf[] getLeaves() {
        return leaves;
    }

    public Queen getQueen() {
        return queen;
    }

    public int getId() {
        return id;
    }

    public ObjectSquare[] getCurrentObj() {
        return currentObj;
    }

    private void trimObjects() {
        int i = 0;
        for (ObjectSquare ob : objects) {
            if (ob != null)
                i++;
            else
                break;
        }
        currentObj = new ObjectSquare[i];
        for (int j = 0; j < i; j++)
            currentObj[j] = objects[j];
    }

    public void spawnAnt() {
        ants[id] = new Ant(queen.getX(), queen.getY() + 1);
        ants[id].setId(id);
        ants[id].setQueenX(queen.getX());
        ants[id].setQueenY(queen.getY());
        passToObjects(ants[id]);
        id++;
    }

    private void passToObjects(ObjectSquare object) {
        for (int i = 0; i < Max.FIELDS; i++)
            if (objects[i] == null) {
                objects[i] = object;
                break;
            }
    }


    private void spawnLeaves() {
        int x[];
        x = ThreadLocalRandom.current().ints(0, Max.SIZE).distinct().limit(Max.LEAVES_USER).toArray();
        int y[];
        y = ThreadLocalRandom.current().ints(0, Max.SIZE).distinct().limit(Max.LEAVES_USER).toArray();

        for (int i = 0; i < Max.LEAVES_USER; i++) {
            if (x[i] != queen.getX() && y[i] != queen.getY())
                leaves[i] = new Leaf(x[i], y[i]);
            else
                leaves[i] = new Leaf(firstNotUsedNumber(x), y[i]);
            passToObjects(leaves[i]);
        }


    }

    private int firstNotUsedNumber(int[] a) {
        List<Integer> accountNumbers = Arrays.stream(a).boxed().collect(Collectors.toList());

        for (int i = 0; i < Max.SIZE; i++)
            if (!accountNumbers.contains(i)) {
                return i;
            }
        return 0;
    }

    private void spawnQueen() {
        queen = new Queen("Queen Elizabeth");
        passToObjects(queen);

    }

    public void moveAnts() {
        trimObjects();

        for (ObjectSquare aCurrentObj : currentObj) {
            if (aCurrentObj.getClassName().equals(new Ant().getClassName())) {
                checkIfTookLeaf(aCurrentObj);
            }

        }
        trimObjects();


    }

    private void checkIfTookLeaf(ObjectSquare objectSquare) {
        boolean holdLeaf = ((Ant) objectSquare).isHoldingLeaf();
        ((Ant) objectSquare).move(currentObj);
        if (((Ant) objectSquare).isHoldingLeaf() != holdLeaf)
            for (ObjectSquare ob : currentObj)
                if (ob.getX() == objectSquare.getX() &&
                        ob.getY() == objectSquare.getY() &&
                        ob.getClassName().equals(new Leaf().getClassName())) {
                    changeLeafState(objectSquare, ob);
                }
    }

    private void changeLeafState(ObjectSquare objectSquare, ObjectSquare ob) {
        ob.visible = false;
        ob.setId(objectSquare.getId());
    }

    public int countLeaves() {
        int counter = 0;
        for (ObjectSquare aCurrentObj : currentObj)
            if (aCurrentObj.getClassName().equals(new Leaf().getClassName()) && !aCurrentObj.visible)
                counter++;
        counter = (leaves.length) - counter;
        return counter;
    }
}
