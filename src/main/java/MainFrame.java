
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MainFrame extends JFrame {
    static final String APP_NAME = "Anthill";
    static final int DELAY = 2000;
    static Anthill anthill;
    static Board b;
    static JFrame f;
    ActionListener refresh = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            nextTurn();
        }
    };

    public static void main(String arg[]) {
        fillMaxClass();
        anthill = new Anthill();
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
        b.getButtonAddAnt().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(anthill.getId()<=Max.MAX_ANTS){
                    anthill.spawnAnt();
                    System.out.println("Spawnieto mrowke"); //TODO Dorób komunikat że za duzo mrowek
                }else
                    System.out.println("Za dużo mrówek"); //TODO Dorób komunikat że za duzo mrowek


            }
        });

        ActionListener taskPerformer = refresh;
        Timer timer = new Timer(DELAY, taskPerformer);
        timer.start();


    }


    private static void nextTurn() {
        f.remove(b.getGui());
        anthill.moveAnts();
        b.passObjects(anthill.getCurrentObj());
        f.add(b.getGui());
        f.revalidate();
    }

    private static void setJFrame() {
        f.add(b.getGui());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setMinimumSize(b.getGui().getMinimumSize());
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.pack();
        f.setVisible(true);

    }


    private static void fillMaxClass() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight = screenSize.getHeight();
        double panelWidth = screenSize.getWidth();
        Max.setMax((int) Math.round(panelWidth), (int) Math.round(panelHeight), 20, 300, 30, 15);
    }


}
