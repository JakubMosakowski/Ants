
import javax.swing.*;
import java.awt.*;


class Main{
    static final String APP_NAME="Anthill";
    static Anthill anthill;
	public static void main(String arg[]){
        fillMaxClass();
        anthill=new Anthill();
        runningJFrame();


	}

    private static void runningJFrame() {
        Runnable r = new Runnable() {
            public void run() {
                Board b = new Board();
                JFrame f = new JFrame(APP_NAME);

                setJFrame(b, f);

                nextTurn(b, f);

            }
        };
        SwingUtilities.invokeLater(r);
    }

    private static void nextTurn(Board b, JFrame f) {
        //TODO jakis system tur niech sie rusza, podadzą do board nową tablice tagów
        f.remove(b.getGui());
        anthill.moveAnts();
        b.showNewTurn();
        f.add(b.getGui());
    }

    private static void setJFrame(Board b, JFrame f) {
        f.add(b.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }


    private static void fillMaxClass() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight=screenSize.getHeight();
        double panelWidth=screenSize.getWidth();
        Max.setMax((int)Math.round(panelWidth),(int)Math.round(panelHeight),10,300,30,5);
    }


}
