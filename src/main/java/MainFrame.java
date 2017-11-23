
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MainFrame extends JFrame{
    static final String APP_NAME="Anthill";
    static final int DELAY = 500;
    static Anthill anthill;
    static Board b;
    static JFrame f;
    ActionListener refresh=new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
           nextTurn();
        }
    };
	public static void main(String arg[]){
        fillMaxClass();
        anthill=new Anthill();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
	}

    public MainFrame() {
                 b = new Board();
                 f = new JFrame(APP_NAME);
                setJFrame();
                anthill.spawnLeaves();
                anthill.spawnQueen();
        ActionListener taskPerformer = refresh;
        Timer timer=new Timer(DELAY, taskPerformer);
        timer.start();


    }



    private static void nextTurn() {
        //TODO jakis system tur niech sie rusza, podadzą do board nową tablice tagów
        f.remove(b.getGui());
        anthill.moveAnts();
        b.showNewTurn();
        f.add(b.getGui());
        f.revalidate();
    }

    private static void setJFrame() {
        f.add(b.getGui());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.pack();
        f.setSize(Max.MAX_X,Max.MAX_Y);
        f.setVisible(true);

    }


    private static void fillMaxClass() {
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight=screenSize.getHeight();
        double panelWidth=screenSize.getWidth();
        Max.setMax((int)Math.round(panelWidth),(int)Math.round(panelHeight),10,300,30,5);
    }


}
