import java.util.Random;
class Leaves{
  public void randomizeLeaves(){
    Random rand=new Random();
    int x;
    int y;
int leaves =5;
    for(int i=0;i<leaves;i++)
    {
      x=rand.nextInt(Max.MAX_X-1)+1;
      y=rand.nextInt(Max.MAX_Y-1)+1;
    }

  }

}
