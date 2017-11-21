
import javax.swing.*;
import java.awt.*;
import java.io.*;


class Main{
    static final String APP_NAME="Anthill";
	public static void main(String arg[]){
		String x,y;
        getScreenSize();
        Board.showJFrame();


		/*

		Anthill anthill=new Anthill(Max.MAX_X/2,Max.MAX_Y/2);

		while(!ants[0].antDies()){
			anthill.print();
			for(int i=0;i<Max.MAX_ANTS;i++){
				anthill.changePath(ants[i].getX(),ants[i].getY(),2);
				ants[i].move();
				anthill.changePath(ants[i].getX(),ants[i].getY(),1);
			}
			System.out.println("It's ants "+ants[0].getMove()+" move!");
			try{
				System.in.read();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("Your ants died.");*/

        System.out.println(Max.MAX_X+"|"+Max.MAX_Y);
        try {
            int z=System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private static void getScreenSize() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight=screenSize.getHeight();
        double panelWidth=screenSize.getWidth();
        Max.setMax((int)Math.round(panelWidth),(int)Math.round(panelHeight),300,30);
    }


}
