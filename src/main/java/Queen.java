public class Queen extends Ant {
    private  String name;
    public static  char TYPE='Q';
    private final int id=0;
    Queen(String queenName){
        super();
        name=queenName;
    }

    protected void setStats(int hp,int dmg){
        super.setStats(hp,dmg);
    }
}
