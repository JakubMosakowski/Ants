import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

class Board {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    private JLabel[][] boardSquares = new JLabel[Max.SIZE][Max.SIZE];
    static char[][] boardSquaresTags = new char[Max.SIZE][Max.SIZE];
    private JPanel board;

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
                //TODO remove that,just for checking
                if ((i + j) % 10 == 0)
                    squareWithAnt(b);
                else if ((i + j) % 10 == 8)
                    squareWithAntQueen(b);
                else if ((i + j) % 10 == 5)
                    squareWithLeaf(b);
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
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        //TODO pozmieniaj te przyciski
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Save")); // TODO - add functionality!
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

                if (boardSquaresTags[i][j] == 'E') {
                    squareEmpty(b);//TODO ma pokazywac puste, tylko test
                    //squareWithAnt(b);
                    //System.out.println(i+"|"+j);
                } else if (boardSquaresTags[i][j] == 'A')
                    squareWithAnt(b);
                else if (boardSquaresTags[i][j] == 'H')
                    squareWithAnthill(b);
                else if (boardSquaresTags[i][j] == 'L')
                    squareWithLeaf(b);
                else
                    squareWithAntQueen(b);

                boardSquares[j][i] = b;

                //TODO remove that,just for checking

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

}