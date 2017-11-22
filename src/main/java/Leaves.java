import java.util.Random;
class Leaf{
    int x;
    int y;
    private static  char TYPE='L';
  public void randomizeLeaves(){
    Random rand=new Random();

    for(int i=0;i<Max.LEAVES;i++)
    {
      x=rand.nextInt(Max.MAX_X-1)+1;
      y=rand.nextInt(Max.MAX_Y-1)+1;
    }

  }

}
