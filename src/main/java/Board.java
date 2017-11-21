import javax.swing.*;
import java.awt.*;

class Board{
	static JFrame f;

    static JPanel  grid=new JPanel();
	public static void showJFrame(){
		f=new JFrame(Main.APP_NAME);
		f.setLayout(null);
		//f.setSize(Max.MAX_X,Max.MAX_Y);
		drawTest();
		f.setVisible(true);
	}

	public static void drawTest(){
        grid.setLayout((new GridLayout(25,25)));
        grid.setSize(10,10);


		for(int i=0;i<25;i++)
			for(int j=0;j<25;j++){
		    grid.add(new JLabel("AAAAA"));

			}
			grid.setVisible(true);
        f.add(grid);
        f.pack();

	}

}
