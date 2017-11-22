public class Queen extends Ant {
    private  String name;
    private static  char TYPE='Q';
    Queen(String queenName){
        super();
        name=queenName;
    }

    protected void setStats(int hp,int dmg){
        super.setStats(hp,dmg);
    }
}
