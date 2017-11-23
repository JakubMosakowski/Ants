import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

class Board {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    private JLabel[][] boardSquares = new JLabel[Max.SIZE][Max.SIZE];
    static char[][] boardSquaresTags = new char[Max.SIZE][Max.SIZE];
    private JPanel board;
    public static char TYPE_EMPTY='E';
    JToolBar tools;
    JButton button;
    Board() {
        initializeGui();
    }


    private final void initializeGui() {
        board = new JPanel(new GridLayout(0, Max.SIZE));
        setToolbar();
        createEmptySquares();
        fillBoard();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));

    }

    private void createEmptySquares() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);
                squareEmpty(b);

                boardSquares[j][i] = b;
                boardSquaresTags[j][i] = 'E';
            }
        }
    }
    private void squareEmpty(JLabel b) {
        ImageIcon icon = new ImageIcon(
                new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
        b.setIcon(icon);
    }

    private void squareWithAnt(JLabel square) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/Ant.png"));
        square.setIcon(icon);
    }

    private void squareWithAntQueen(JLabel square) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/AntQueen.png"));
        square.setIcon(icon);
    }

    private void squareWithLeaf(JLabel square) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/Leaf.png"));
        square.setIcon(icon);
    }

    private void squareWithAnthill(JLabel square) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/Anthill.png"));
        square.setIcon(icon);
    }

    private void fillBoard() {
        gui.remove(board);
        board.removeAll();

        for (int ii = 0; ii < Max.SIZE; ii++) {
            for (int jj = 0; jj < Max.SIZE; jj++) {
                board.add(boardSquares[jj][ii]);
            }
        }
        gui.add(board);
    }

    private void setToolbar() {
        tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        //TODO pozmieniaj te przyciski
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.addSeparator();

        button =new JButton("Save");

        tools.add(button); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
    }

     void showNewTurn() {
        for (int i = 0; i < boardSquares.length; i++) {
            for (int j = 0; j < boardSquares[i].length; j++) {
                JLabel b = new JLabel();
                b.setOpaque(true);

                if (boardSquaresTags[i][j] == Queen.TYPE) {
                    squareWithAntQueen(b);
                } else if (boardSquaresTags[i][j] == Ant.TYPE)
                    squareWithAnt(b);
                else if (boardSquaresTags[i][j] == Anthill.TYPE)
                    squareWithAnthill(b);
                else if (boardSquaresTags[i][j] == Leaf.TYPE)
                    squareWithLeaf(b);
                else
                    squareEmpty(b);

                boardSquares[j][i] = b;


            }

        }
        fillBoard();

    }




    public final JComponent getBoard() {
        return board;
    }

    public final JComponent getGui() {
        return gui;
    }
    public final JComponent getTools() {
        return tools;
    }
    public final JComponent getButton() {
        return button;
    }

}