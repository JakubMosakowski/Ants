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

    protected Ant ants[] = new Ant[Max.MAX_ANTS_USER];
    protected Leaf leaves[] = new Leaf[Max.LEAVES_USER];
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
        spawnLeaves();
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
        ants[id].setId(id);
        ants[id].setQueenX(queen.getX());
        ants[id].setQueenY(queen.getY());
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
        for (int i = 0; i < Max.LEAVES_USER; i++) {
            leaves[i] = new Leaf();
            passToObjects(leaves[i]);
        }


    }

    public void spawnQueen() {
        queen = new Queen("Queen Elizabeth");
        passToObjects(queen);

    }


    public void moveAnts() {
        trimObjects();

        for (int i = 0; i < currentObj.length; i++) {
           if(currentObj[i].getClassName().equals(new Ant().getClassName())){
               checkIfTookLeaf(currentObj[i]);
            }

        }
        trimObjects();


    }

    private void checkIfTookLeaf(ObjectSquare objectSquare) {
        boolean holdLeaf=((Ant) objectSquare).isHoldingLeaf();
        ((Ant) objectSquare).move(currentObj);
        if(((Ant) objectSquare).isHoldingLeaf()!=holdLeaf)
             for(ObjectSquare ob:currentObj)
                 if(ob.getX()== objectSquare.getX()&&
                         ob.getY()== objectSquare.getY()&&
                         ob.getClassName().equals(new Leaf().getClassName())){
                     changeLeafState(objectSquare, ob);
                 }
    }

    private void changeLeafState(ObjectSquare objectSquare, ObjectSquare ob) {
        ob.visible=false;
        ((Leaf)ob).setRaised(true);
        ob.setId(objectSquare.getId());
    }

    public int countLeaves() {
        int counter=0;
        for(int i=0;i<currentObj.length;i++)
            if(currentObj[i].getClassName().equals(new Leaf().getClassName()) && !currentObj[i].visible)
                counter++;
        counter=(leaves.length)-counter;
        return counter;
    }
}
