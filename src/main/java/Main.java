
import javax.swing.*;
import java.awt.*;


class Main{
    static final String APP_NAME="Anthill";
	public static void main(String arg[]){
        getScreenSize();
        setJFrame();


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
	}

    private static void setJFrame() {
        Runnable r = new Runnable() {
            public void run() {
                Board cb = new Board();
                JFrame f = new JFrame(APP_NAME);
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    private static void getScreenSize() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight=screenSize.getHeight();
        double panelWidth=screenSize.getWidth();
        Max.setMax((int)Math.round(panelWidth),(int)Math.round(panelHeight),300,30);
    }


}
