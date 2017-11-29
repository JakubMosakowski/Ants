
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;


class MainFrame extends JFrame {
    static final String APP_NAME = "Anthill";
    static final int DELAY = 200;
    static Anthill anthill;
    static Board b;
    static JFrame f;
    private int seconds=0;
    private long millis =0;
    static LocalTime time;
    static Timer timerRefresher;
    ActionListener refresh = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timeManagement();
            nextTurn();
        }
    };

    private void timeManagement() {
        millis +=DELAY;
        if(millis %1000==0)
            seconds++;
        time= LocalTime.ofSecondOfDay(seconds);
        b.getTimeCounter().setText(time.toString());
    }


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
                if(anthill.getId()<Max.MAX_ANTS){
                    anthill.spawnAnt();
                    b.getCommunicationLabel().setText("Ant has been spawned");
                    b.getAntsCounter().setText("Ants:"+anthill.getId());
                }else
                    b.getCommunicationLabel().setText("Too many ants!(Max="+Max.MAX_ANTS+")");
            }
        });
        b.getButtonStartAgain().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                startAgain();//TODO
            }
        });


        setTimer();
    }

    private void setTimer() {
        ActionListener boardRefresher = refresh;
        timerRefresher = new Timer(DELAY, boardRefresher);
        timerRefresher.start();
    }


    private static void nextTurn() {
        anthill.moveAnts();
        if(anthill.countLeaves()==0){
            ending();

        }
        b.getLeavesCounter().setText("Leaves:"+anthill.countLeaves());
        f.remove(b.getGui());
        b.passObjects(anthill.getCurrentObj());
        f.add(b.getGui());
        f.revalidate();
    }

    private static void ending() {
        timerRefresher.stop();
        JFrame jf=new JFrame();
        JPanel jp=new JPanel();
        jp.add(new JLabel("Ant's colledted all leaves! Their time:"+time.toString()));
        jf.add(jp);
        jf.setLocationByPlatform(true);
        jf.pack();
        jf.setVisible(true);
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
        Max.setMax((int) Math.round(panelWidth), (int) Math.round(panelHeight), 30, 300, 30, 15);
    }


}
