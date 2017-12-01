import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.LocalTime;


class MainFrame extends JFrame {
    static final String APP_NAME = "Anthill";
    static Anthill anthill;
    static Board b;
    static JFrame f;
    private int seconds = 0;
    private long millis = 0;
    static LocalTime time;
    static Timer timerRefresher;
    ActionListener refresh = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timeManagement();
            nextTurn();
        }
    };

    public MainFrame() {
        b = new Board();
        f = new JFrame(APP_NAME);
        setJFrame();
        b.getButtonAddAnt().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (anthill.getId() < Max.MAX_ANTS_USER) {
                    anthill.spawnAnt();
                    b.getCommunicationLabel().setText("Ant has been spawned");
                    b.getAntsCounter().setText("Ants:" + anthill.getId());
                } else
                    b.getCommunicationLabel().setText("Too many ants!(Max=" + Max.MAX_ANTS_USER + ")");
            }
        });
        b.getButtonStartAgain().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startAgain();
            }
        });

        b.getButtonOptions().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showOptions();
            }
        });
        setTimer();
    }

    public static void main(String arg[]) {
        fillMaxClass(30, 15, 200);
        anthill = new Anthill();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    private void timeManagement() {
        millis += Max.DELAY_USER;
        seconds = (int) millis / 1000;
        time = LocalTime.ofSecondOfDay(seconds);
        b.getTimeCounter().setText(time.toString());
    }

    private void showOptions() {
        timerRefresher.stop();
        JDialog jd = new JDialog();
        JPanel jp = new JPanel();

        NumberFormat format = NumberFormat.getInstance();
        JLabel antsLabel = new JLabel("(No more than 30) Max number of ants:");
        NumberFormatter formatter = new NumberFormatter(format);
        JFormattedTextField ants;

        formatter.setAllowsInvalid(false);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(30);
        ants = new JFormattedTextField(formatter);
        ants.setText(String.valueOf(Max.MAX_ANTS_USER));
        ants.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE)
                    ants.setText("0");
            }
        });
        JLabel leavesLabel = new JLabel("(No more than 30) Max number of leaves:");
        JFormattedTextField leaves;

        leaves = new JFormattedTextField(formatter);
        leaves.setText(String.valueOf(Max.LEAVES_USER));
        leaves.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE)
                    leaves.setText("0");
            }
        });


        JLabel delayLabel = new JLabel("(100ms-9999ms) Delay:");
        NumberFormatter formatter2 = new NumberFormatter(format);
        JFormattedTextField delay;

        formatter2.setMaximum(9999);
        formatter2.setAllowsInvalid(false);
        formatter2.setValueClass(Integer.class);
        delay = new JFormattedTextField(formatter2);
        delay.setText(String.valueOf(Max.DELAY_USER));
        delay.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE)
                    delay.setText("0");
            }
        });

        JButton accept = new JButton("Ok");
        setAcceptButton(jd, ants, leaves, delay, accept);


        jp.add(antsLabel);
        jp.add(ants);
        jp.add(delayLabel);
        jp.add(delay);
        jp.add(leavesLabel);
        jp.add(leaves);
        jp.add(accept);
        jd.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                accept.doClick();
            }
        });
        setJDialog(jd, jp);
    }

    private void setAcceptButton(JDialog jd, JFormattedTextField ants, JFormattedTextField leaves, JFormattedTextField delay, JButton accept) {
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (delay.getText().length() < 3) {
                    jd.setTitle("Delay minimum is 100");
                } else {
                    if (Integer.parseInt(ants.getText()) == 0)
                        ants.setText("1");
                    if (Integer.parseInt(leaves.getText()) == 0)
                        leaves.setText("1");
                    String str = delay.getText().replace(",", "");
                    Max.setLeavesUser(Integer.parseInt(leaves.getText()));
                    Max.setDelayUser(Integer.parseInt(str));
                    Max.setMaxAntsUser(Integer.parseInt(ants.getText()));
                    timerRefresher.start();
                    jd.setVisible(false);
                    jd.dispose();
                }

            }
        });
    }

    private void setJDialog(JDialog jd, JPanel jp) {
        jd.setMinimumSize(new Dimension(300, 100));
        jd.setAlwaysOnTop(true);
        jd.setLocationRelativeTo(null);
        jd.setResizable(false);
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        jd.setTitle("Changes will apply in next game");
        jd.add(jp);
        jd.setLocationByPlatform(true);
        jd.pack();
        jd.setVisible(true);
    }

    private void startAgain() {
        seconds = 0;
        millis = 0;
        fillMaxClass(Max.MAX_ANTS_USER, Max.LEAVES_USER, Max.DELAY_USER);
        anthill = new Anthill();
        timerRefresher.stop();
        setJFrame();
        setTimer();
    }

    private void setTimer() {
        ActionListener boardRefresher = refresh;
        timerRefresher = new Timer(Max.DELAY_USER, boardRefresher);
        timerRefresher.start();
    }


    private static void nextTurn() {
        anthill.moveAnts();
        if (anthill.countLeaves() == 0) {
            ending();

        }
        b.getLeavesCounter().setText("Leaves:" + anthill.countLeaves());
        f.remove(b.getGui());
        b.passObjects(anthill.getCurrentObj());
        f.add(b.getGui());
        f.revalidate();
    }

    private static void ending() {
        timerRefresher.stop();
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jp.add(new JLabel("Ant's collected all leaves! Their time:" + time.toString()));

        jf.add(jp);
        jf.setLocationByPlatform(true);
        jf.pack();
        jf.setVisible(true);
    }

    private static void setJFrame() {
        f.add(b.getGui());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(b.getGui().getMinimumSize());
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.pack();
        f.setVisible(true);
        b.getAntsCounter().setText("");
        b.getCommunicationLabel().setText("");
    }

    private static void fillMaxClass(int ants, int leaves, int delay) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double panelHeight = screenSize.getHeight();
        double panelWidth = screenSize.getWidth();
        Max.setMax((int) Math.round(panelWidth), (int) Math.round(panelHeight), ants, 300, 30, leaves, delay);
    }


}
